#!/bin/sh
LOCALE=en_US
PHONES=`cat ../input/polly_phones/$LOCALE/PHONES`
VOICENAME=joanna
VOICESTYLE=s
VOICE=$VOICENAME"_"$VOICESTYLE
VOICEMODE=default
for p in $PHONES
do
  ls -l ../input/polly_phones/$LOCALE/$VOICE/$VOICEMODE/$p".mp3"
done

