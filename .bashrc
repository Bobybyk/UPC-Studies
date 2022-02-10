#
# ~/.bashrc
#

[[ $- != *i* ]] && return

colors() {
	local fgc bgc vals seq0

	printf "Color escapes are %s\n" '\e[${value};...;${value}m'
	printf "Values 30..37 are \e[33mforeground colors\e[m\n"
	printf "Values 40..47 are \e[43mbackground colors\e[m\n"
	printf "Value  1 gives a  \e[1mbold-faced look\e[m\n\n"

	# foreground colors
	for fgc in {30..37}; do
		# background colors
		for bgc in {40..47}; do
			fgc=${fgc#37} # white
			bgc=${bgc#40} # black

			vals="${fgc:+$fgc;}${bgc}"
			vals=${vals%%;}

			seq0="${vals:+\e[${vals}m}"
			printf "  %-9s" "${seq0:-(default)}"
			printf " ${seq0}TEXT\e[m"
			printf " \e[${vals:+${vals+$vals;}}1mBOLD\e[m"
		done
		echo; echo
	done
}

[ -r /usr/share/bash-completion/bash_completion ] && . /usr/share/bash-completion/bash_completion

# Change the window title of X terminals
case ${TERM} in
	xterm*|rxvt*|Eterm*|aterm|kterm|gnome*|interix|konsole*)
		PROMPT_COMMAND='echo -ne "\033]0;${USER}@${HOSTNAME%%.*}:${PWD/#$HOME/\~}\007"'
		;;
	screen*)
		PROMPT_COMMAND='echo -ne "\033_${USER}@${HOSTNAME%%.*}:${PWD/#$HOME/\~}\033\\"'
		;;
esac

use_color=true

# Set colorful PS1 only on colorful terminals.
# dircolors --print-database uses its own built-in database
# instead of using /etc/DIR_COLORS.  Try to use the external file
# first to take advantage of user additions.  Use internal bash
# globbing instead of external grep binary.
safe_term=${TERM//[^[:alnum:]]/?}   # sanitize TERM
match_lhs=""
[[ -f ~/.dir_colors   ]] && match_lhs="${match_lhs}$(<~/.dir_colors)"
[[ -f /etc/DIR_COLORS ]] && match_lhs="${match_lhs}$(</etc/DIR_COLORS)"
[[ -z ${match_lhs}    ]] \
	&& type -P dircolors >/dev/null \
	&& match_lhs=$(dircolors --print-database)
[[ $'\n'${match_lhs} == *$'\n'"TERM "${safe_term}* ]] && use_color=true

if ${use_color} ; then
	# Enable colors for ls, etc.  Prefer ~/.dir_colors #64489
	if type -P dircolors >/dev/null ; then
		if [[ -f ~/.dir_colors ]] ; then
			eval $(dircolors -b ~/.dir_colors)
		elif [[ -f /etc/DIR_COLORS ]] ; then
			eval $(dircolors -b /etc/DIR_COLORS)
		fi
	fi

	if [[ ${EUID} == 0 ]] ; then
		PS1='\[\033[01;31m\][\h\[\033[01;36m\] \W\[\033[01;31m\]]\$\[\033[00m\] '
	else
		PS1='\[\033[01;32m\][\u@\h\[\033[01;37m\] \W\[\033[01;32m\]]\$\[\033[00m\] '
	fi

	alias ls='ls --color=auto'
	alias grep='grep --colour=auto'
	alias egrep='egrep --colour=auto'
	alias fgrep='fgrep --colour=auto'
else
	if [[ ${EUID} == 0 ]] ; then
		# show root@ when we don't have colors
		PS1='\u@\h \W \$ '
	else
		PS1='\u@\h \w \$ '
	fi
fi

unset use_color safe_term match_lhs sh

alias cp="cp -i"                          # confirm before overwriting something
alias df='df -h'                          # human-readable sizes
alias free='free -m'                      # show sizes in MB
alias np='nano -w PKGBUILD'
alias more=less

xhost +local:root > /dev/null 2>&1

complete -cf sudo

# Bash won't get SIGWINCH if another process is in the foreground.
# Enable checkwinsize so that bash will check the terminal size when
# it regains control.  #65623
# http://cnswww.cns.cwru.edu/~chet/bash/FAQ (E11)
shopt -s checkwinsize

shopt -s expand_aliases

# export QT_SELECT=4

# Enable history appending instead of overwriting.  #139609
shopt -s histappend

#
# # ex - archive extractor
# # usage: ex <file>
ex ()
{
  if [ -f $1 ] ; then
    case $1 in
      *.tar.bz2)   tar xjf $1   ;;
      *.tar.gz)    tar xzf $1   ;;
      *.bz2)       bunzip2 $1   ;;
      *.rar)       unrar x $1     ;;
      *.gz)        gunzip $1    ;;
      *.tar)       tar xf $1    ;;
      *.tbz2)      tar xjf $1   ;;
      *.tgz)       tar xzf $1   ;;
      *.zip)       unzip $1     ;;
      *.Z)         uncompress $1;;
      *.7z)        7z x $1      ;;
      *)           echo "'$1' cannot be extracted via ex()" ;;
    esac
  else
    echo "'$1' is not a valid file"
  fi
}
###### OLD
alias local="cd /srv/http"
#####


###################
###### SHORT ######
###################
alias ll="ls -lh"
alias la="ls -a"
alias writer="libreoffice --writer"   # Lance le traitement de texte
alias calc="libreoffice --calc"     # Lance le tableur
alias impress="libreoffice --impress"  # Lance l'éditeur de présentation
alias draw="libreoffice --draw"     # Lance l'éditeur graphique
alias base="libreoffice --base"     # Lance le gestionnaire de base de données
alias math="libreoffice --math"     # Lance l'éditeur de formule
alias f="find . -iname"
alias bashrc="subl ~/.bashrc"
alias config="subl ~/.ssh/config"
alias killn="kill-name"
kill-name() {
    kill $(pgrep $1)
}
alias start="./on_startup.sh"
### tp3 SY5
alias listar="/home/sha/Repo/sy5_2021-2022/TP/TP3/work/listar"
alias detar="/home/sha/Repo/sy5_2021-2022/TP/TP3/work/detar"
alias p="python3"
alias dpll="/home/sha/Projets/lo5---solveur-dpll-recursif/dpll"

###################
##### Réseau ######
###################

###### VPN
alias uvpn="sudo openvpn /etc/openvpn/ovpn_udp/$(ls /etc/openvpn/ovpn_udp/ | shuf -n 1)"
alias fuvpn="sudo openvpn /etc/openvpn/ovpn_udp/$(ls /etc/openvpn/ovpn_udp/ | egrep fr | shuf -n 1)"
alias fiuvpn="sudo openvpn /etc/openvpn/ovpn_udp/$(ls /etc/openvpn/ovpn_udp/ | egrep fi | shuf -n 1)"
alias kuvpn="sudo openvpn /etc/openvpn/ovpn_udp/$(ls /etc/openvpn/ovpn_udp/ | egrep uk | shuf -n 1)"
alias tvpn="sudo openvpn /etc/openvpn/ovpn_tcp/$(ls /etc/openvpn/ovpn_tcp/ | shuf -n 1)"

###### ssh
alias lucy="ssh lefrancm@lucy.informatique.univ-paris-diderot.fr"
alias lulu="ssh lefrancm@lulu -J lefrancm@lucy.informatique.univ-paris-diderot.fr"
alias nivose="ssh lefrancm@nivose.informatique.univ-paris-diderot.fr"
alias pi="ssh pi@sha-webserver.ddns.net"
tput setaf 1; 
echo "Ctrl+D to exit ssh login"
tput setaf 7;
if [[ "$SSH_AUTH_SOCK" = "" ]]; then
    # on the first round, we do this...
    exec ssh-agent bash
else
    # ... and when ssh-agent is running, we do this instead.
    ssh-add
fi

###################
#####  SYST  ######
###################
alias reload="exec $SHELL"
alias majm='sudo pacman-mirrors -g && sudo pacman -Syyu && yaourt -Syua'
alias maj='sudo pacman -Syyu && yaourt -Syua'
alias sy="sudo rm /var/lib/pacman/sync/*"
alias tok='echo "JF9-OBV-GWY-ZT1"'###################
alias sd="shutdown now"
alias clean="sudo pacman -Qdt ; sudo pacman -Sc"
alias frm="shred -zvu -n 10"
alias path="echo $PATH"
alias flush="sudo nscd -i hosts | echo 'succesfully flushed DNS'"
# provisoire, faire script bash
alias erase="echo 'zero.small.file creation (102 400b)' ; dd if=/dev/zero of=zero.small.file bs=1024 count=102400 ; shred -zv zero.small.file ; echo 'zero.file filling...' ; cat /dev/urandom > zero.file ; sync ; echo 'removing zero.small.file' ; rm zero.small.file ; shred -zv zero.file ; sync ; echo 'removing zero.file' ; rm zero.file"
alias conk="conky -c ~/.config/conky/conky.conkyrc"
eval `opam env`
#sudo systemctl mask sleep.target suspend.target 	

###################
######  GIT  ######
###################
alias gitlog="git config --global user.email matt.lefranc@gmail.com ; git config --global user.name 'Matthieu Le Franc'"
alias gitlist="git config --list"
alias pushm="git push origin master"