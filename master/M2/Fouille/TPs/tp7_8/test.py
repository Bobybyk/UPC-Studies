# coding=utf-8
import importlib
import inspect
import math
import re
import signal
import sys
from scipy.stats import binom


class TestError(Exception):
  pass


class TimedOut(Exception):
  pass


def has_function(module, name):
  return name in dir(module) and callable(getattr(module, name))


def trim(x):
  s = str(x)
  if len(s) < 100:
    return s
  return s[:44] + '...[snip]...' + s[-44:]


def signal_handler(signum, frame):
  # TODO(viger): Catch or log signum/frame?
  raise TimedOut('Timed out!')


def timeout(deadline, f, repeat, *args):
  signal.signal(signal.SIGALRM, signal_handler)
  signal.alarm(deadline)  # In seconds
  try:
    if repeat == 1:
      r = f(*args)
    else:
      r = [f(*args) for _ in range(repeat)]
    signal.alarm(0)  # Cancel the alarm
    return r
  except Exception as e:
    signal.alarm(0)  # Cancel the alarm
    return e


def safe_import(module_name, deadline=10):
  module=timeout(deadline, importlib.import_module, 1, module_name)
  if isinstance(module, Exception):
    print(str(module))
    print('SCORE: 0')
    sys.exit(1)
  return module


def Eq(x, y):
  if x == y:
    return True
  print("FAILED: Expected: '%s'.\n        Actual  : '%s'" % (trim(y),
                                                             trim(x)))
  return False


def FloatNear(x, y, eps):
  if y == 0:
    if math.fabs(x) > eps:
      print('FAILED: Expected: 0. Actual: %s' % x)
      return False
    return True
  if math.fabs(x - y) <= eps * (math.fabs(x) + math.fabs(y)):
    return True
  print("FAILED: Expected: '%s'.\n        Actual  : '%s'\n"
        "        Delta   : %s (relative error: %.2f%%)" % (
          y, x, x-y, 100*math.fabs(x-y)/math.fabs(y)))
  return False


def FloatEq(x, y):
  return FloatNear(x, y, 1e-9)


def FloatListEq(lx, ly):
  ny = len(ly)
  nx = len(lx)
  if nx != ny:
    print('FAILED: Expected list of %d elements, got %d.' % (ny, nx))
    return False
  for i in range(nx):
    if not FloatEq(lx[i], ly[i]):
      print('On element #%d' % i)
      return False
  return True


def FloatDictEq(dx, dy):
  ny = len(dy)
  nx = len(dx)
  if nx != ny:
    print('FAILED: Expected dict of %d elements, got %d.' % (ny, nx))
    return False
  for k, v in dx.items():
    if k not in dy:
      print('FAILED: Key "%s" should be in the dictionary. It is not.' % k)
      return False
    ok = FloatEq(v, dy[k])
    if not ok:
      print('For value mapped to key "%s"' % k)
      return False
  return True


def FloatNearPred(eps):
  return lambda x, y: FloatNear(x, y, eps)


def IsBetween(x, interval):
  lo, hi = interval
  if x >= lo and x <= hi:
    return True
  print(
      "FAILED: Expected in interval: [%s, %s].\n           Actual value     : '%s'"
      % (lo, hi, x))
  return False


def assertEq(x, y):
  if x != y:
    raise TestError("Expected value: '%s'.\nActual value  : '%s'" % (y, x))


def PrintSep():
  print('=====================================================')


def InInterval(x, l):
  """l = intervals, concatenated. Eg [2,5,7,9] is: {2,3,4} union {7,8}."""
  for i in range(len(l)//2):
    if x >= l[2*i] and x < l[2*i+1]:
      return True
  print('FAILED: value %s is not in interval list %s' % (x, l))
  return False


def ScoreTime(time_table, t):
  """Maps a time to a predefined score table. Interpolates logarithmically."""
  assert time_table[-1][1] == 0
  assert t > 1e-99
  # Add sentinels
  local_time_table = [(1e-99,time_table[0][1])] + time_table + [(1e99,0)]
  for i in range(len(local_time_table) - 1):
    if t >= local_time_table[i][0] and t < local_time_table[i + 1][0]:
      loga = math.log(local_time_table[i][0])
      logb = math.log(local_time_table[i + 1][0])
      logt = math.log(t)
      scorea = local_time_table[i][1]
      scoreb = local_time_table[i + 1][1]
      return scorea + (scoreb - scorea) * (logt - loga) / (logb - loga)


def simple_binomial_confidence_interval(n, p, eps):
  """Interval in which we have a 1-eps proba of falling with binom(n, p)."""
  assert eps < 1
  ret = []
  eps *= 0.5
  lo = 0
  hi = int(n * p)
  while lo + 1 < hi:
    mid = (lo + hi) // 2
    if binom.cdf(mid, n, p) > eps:
      hi = mid
    else:
      lo = mid
  ret.append(mid)
  lo = int(n * p)
  hi = n
  while lo + 1 < hi:
    mid = (lo + hi) // 2
    if binom.sf(mid, n, p) > eps:
      lo = mid
    else:
      hi = mid
  ret.append(mid)
  return ret

# Raises OSError if the source can't be retrieved.
def SourceCodeWithoutComments(function):
  src_lines = inspect.getsourcelines(function)[0]
  # Remove the docstring from the source code.
  end_docstring = None
  if len(src_lines) > 2 and re.search(r'^\s*"""', src_lines[1]):
    # Special case: single-line docstring.
    if re.search(r'^\s*""".*"""', src_lines[1]):
      end_docstring = 1
    else:
      for i in range(2, len(src_lines)):
        if re.search(r'^.*"""\s*$', src_lines[i]):
          end_docstring  = i
          break
  if end_docstring is not None:
    src_lines = src_lines[0:1] + src_lines[end_docstring+1:]
  # Remove comment lines.
  src_lines = [l for l in src_lines if not re.search(r'^\s*#', l)]
  return ''.join(src_lines)


def str_args(l):
  return ', '.join([trim(x) for x in l])


def Test(module, function_name, deadline=1, pred=Eq, data=[], repeat=1, penalty_per_keyword={}):
  PrintSep()
  if not has_function(module, function_name):
    print('Not testing "%s()": not found in "%s.py"' % (function_name, module))
    return 0
  print('Testing "%s()"' % function_name)
  function = getattr(module, function_name)
  s = 0
  if penalty_per_keyword:
    # Will raise OSError if the source can't be retrieved -- in that case, we do
    # want a hard failure.
    src_code = SourceCodeWithoutComments(function)
    for word,penalty in penalty_per_keyword.items():
      if re.search(r'\b%s\b' % re.escape(word), src_code):
        s -= penalty
        print('Penalty: -%s for forbidden keyword "%s"' % (penalty, word))
  i = 0
  for t in data:
    result = timeout(deadline, function, repeat, *t[:-2])
    if isinstance(result, Exception):
      print('ERROR on test #%d: %s\nOn input: %s' % (i, result, str_args(t[:-2])))
      break
    failure=None
    try:
      if pred(result, t[-2]):
        print('PASSED test #%d' % i)
        s += t[-1]
        i += 1
      else:
        failure = 'FAILED'
    except Exception as e:
      failure = 'EXCEPTION: %s' % e
    if failure:
      print('%s in test #%d. Input: %s' % (failure, i, str_args(t[:-2])))
      break
  if s < 0:
    s = 0
  return s


# Similar, but there is no 'expected result'. Rather, the result is ignored
# (unless it's an exception), but we run an expected predicate on the
# function's argument. This is nice when the function is writing its
# output(s) in the arguments.
def Test2(module, function_name, deadline=1, data=[]):
  PrintSep()
  if not has_function(module, function_name):
    print('Not testing "%s()": not found in "%s.py"' % (function_name, module))
    return 0
  print('Testing "%s()"' % function_name)
  function = getattr(module, function_name)
  s = 0
  i = 0
  for t in data:
    result = timeout(deadline, function, 1, *t[:-2])
    if isinstance(result, Exception):
      print('ERROR on test #%d: %s\nInput argument(s): %s' % (i, result, str_args(t[:-2])))
      break
    failure=None
    try:
      if t[-2](*t[:-2]):
        print('PASSED test #%d' % i)
        s += t[-1]
        i += 1
      else:
        failure = 'FAILED'
    except Exception as e:
      failure = 'EXCEPTION: %s' % e
    if failure:
      print('%s in test #%d: %s' % (failure, i, str_args(t[:-2])))
      break
  return s
