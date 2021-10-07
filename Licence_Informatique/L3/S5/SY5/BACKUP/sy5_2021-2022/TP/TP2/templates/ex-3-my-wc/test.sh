#!/bin/bash

GLOBAL_SUCCESS=true

if [ "plus" == "$1" ]; then
    PLUS=true
else
    PLUS=false
fi

echo_red() {
  if [ -t 1 ] && command -v tput >/dev/null 2>&1; then
    echo -n "$(tput setaf 1)$(tput bold)" 2>/dev/null
    echo $@
    echo -n "$(tput sgr0)" 2>/dev/null
  else
    echo $@
  fi
}

compare_with_wc() {
    local SUCCESS=true
    local TMP_MY=$(mktemp)
    local TMP_SYS=$(mktemp)
    # --- Ugly hack ---
    local ARGS_SYS=($@)
    if ! $PLUS; then
       if ! echo ${ARGS_SYS[@]} | grep -E '(^|\s)(-l|-c|-lc|-cl)($|\s)' >/dev/null 2>&1; then
           ARGS_SYS=("-lc" ${ARGS_SYS[@]})
       fi
    fi
    # -----------------
    
    if [ -z "$INPUT" ]; then
        echo -n "Running \`./my-wc $@\`... "
        ./my-wc $@ > "$TMP_MY" 2>/dev/null
        local RES_MY=$?
        wc ${ARGS_SYS[@]} >/dev/null 2>&1
        local RES_SYS=$?
        wc ${ARGS_SYS[@]} 2>/dev/null | sed -E 's/^[[:blank:]]*//;s/[[:blank:]]*$//;s/[[:space:]]+/	/g;' > "$TMP_SYS" 2>/dev/null
    else
        echo -n "Running \`echo '$INPUT' | ./my-wc $@\`... "
        echo "$INPUT" | ./my-wc $@ > "$TMP_MY" 2>/dev/null
        local RES_MY=$?
        echo "$INPUT" | wc ${ARGS_SYS[@]} >/dev/null 2>&1
        local RES_SYS=$?
        echo "$INPUT" | wc ${ARGS_SYS[@]} 2>/dev/null | sed -E 's/^[[:blank:]]*//;s/[[:blank:]]*$//;s/[[:space:]]+/	/g;' > "$TMP_SYS" 2>/dev/null
    fi
    if [ $RES_SYS -ne 0 ]; then
        RES_SYS=1
    fi
    cmp --silent "$TMP_MY" "$TMP_SYS"  >/dev/null 2>&1
    OUT=$?
    if [ $RES_MY -eq $RES_SYS -a $OUT -eq 0 ]; then
        echo "OK"
    else
        SUCCESS=false
        GLOBAL_SUCCESS=false
        echo_red "Failed"
    fi
    if [ ! $RES_MY -eq $RES_SYS ]; then
        echo " - Expected exit code $RES_SYS, got $RES_MY"
    fi
    if [ ! $OUT -eq 0 ]; then
        if [ $(dd "if=$TMP_SYS" bs=101 count=1 2>/dev/null | wc -c 2>/dev/null) -le 100 ]; then
            echo " - Expected output:"
            cat "$TMP_SYS"
            echo " - Actual output:"
            dd "if=$TMP_MY" bs=100 count=1 2>/dev/null
            if [ $(dd "if=$TMP_MY" bs=101 count=1 2>/dev/null | wc -c) -gt 100 ]; then
                echo "[TRUNCATED]"
            fi
        else
            echo " - Wrong output"
        fi
    fi

    if ! $SUCCESS ; then
        echo ""
    fi
}

check_valgrind() {
    echo -n "Running \`valgrind --leak-check=full ./my-wc $@\`... "
    valgrind --leak-check=full --error-exitcode=33 ./my-wc $@ >/dev/null 2>&1
    RES=$?
    if [ "$RES" -eq 33 ]; then
        echo_red "Failed"
    else
        echo "OK"
    fi
}

INPUT='Hello, world!'
compare_with_wc "-lc"
compare_with_wc "-l"
compare_with_wc "-c"
if echo "A" | wc - >/dev/null 2>&1; then compare_with_wc "-lc" "-"; fi
INPUT=
compare_with_wc "-l" "my-wc.c"
compare_with_wc "-c" "my-wc.c"
compare_with_wc "-lc" "my-wc.c"
rm -rf NONEXISTENT_FILE
compare_with_wc "-l" "NONEXISTENT_FILE"
compare_with_wc "my-wc.c"

if $PLUS; then
    compare_with_wc "-w" "my-wc.c"
    compare_with_wc "-lw" "my-wc.c"
    compare_with_wc "-wc" "my-wc.c"
    compare_with_wc "-lwc" "my-wc.c"
fi

if command -v valgrind >/dev/null 2>&1; then
    check_valgrind my-wc.c
    rm -rf NONEXISTENT_FILE
    check_valgrind NONEXISTENT_FILE
else
    echo "Could not run memory leaks check: please install valgrind on this computer if you can, otherwise ignore this line."
fi
