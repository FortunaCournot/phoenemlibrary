#!/bin/sh
LOCALE=en_US
PHONES=`cat ../input/polly_phones/$LOCALE/PHONES`
VOICENAME=joanna
VOICESTYLE=s
VOICE=$VOICENAME"_"$VOICESTYLE
VOICEMODE=default

SOURCEDIR=../input/polly_phones/$LOCALE/$VOICE/$VOICEMODE
TARGETDIR=../result/ue4phones/$LOCALE/$VOICE/$VOICEMODE

mkdir -p $TARGETDIR
rm -f $TARGETDIR/*.wav

for p in $PHONES
do
    ffmpeg -hide_banner -i $SOURCEDIR/$p".mp3" -af "silenceremove=start_periods=0:start_duration=0.05:start_threshold=-60dB:detection=peak,aformat=dblp,areverse,silenceremove=start_periods=0:start_duration=0.05:start_threshold=-60dB:detection=peak,aformat=dblp,areverse" $TARGETDIR/$p".wav"
done

