#!/usr/bin/env bash

set -e

function download_chrome() {
  echo "Downloading chrome"
  wget -qO chrome_setup.deb "https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb"
  sudo apt install ./chrome_setup.deb
}

function download_firefox() {
  echo "Downloading firefox"
  wget -qO firefox.tar.bz2 "https://download.mozilla.org/?product=firefox-latest&os=linux64&lang=en-US"
  tar xjfv firefox.tar.bz2
  sudo mv firefox /opt
  sudo ln -s /opt/firefox/firefox /usr/local/bin/firefox
}

if [ "$1" == "chrome" ]; then
  download_chrome
elif [ "$1" == "firefox" ]; then
  download_firefox
else
  echo "No browsers to download, pass browser name, chrome or firefox"
fi
