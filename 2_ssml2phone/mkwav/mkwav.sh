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

THRESHOLD=-54dB

echo "METADATA" $TARGETDIR >$TARGETDIR/metadata.txt
for p in $PHONES
do
    ffmpeg -hide_banner -i $SOURCEDIR/$p".mp3" -af "silenceremove=start_periods=1:start_duration=0:start_threshold="$THRESHOLD":detection=peak,aformat=dblp,areverse,silenceremove=start_periods=1:start_duration=0:start_threshold="$THRESHOLD":detection=peak,aformat=dblp,areverse" $TARGETDIR/$p".wav"
    echo $p >>$TARGETDIR/metadata.txt
    ffmpeg -hide_banner -i $SOURCEDIR/$p".mp3" 2>&1 | grep Duration| sed 's/.*Duration: \(.*\), start/\1/g' >>$TARGETDIR/metadata.txt
    ffmpeg -hide_banner -i $TARGETDIR/$p".wav" 2>&1 | grep Duration| sed 's/.*Duration: \(.*\), start/\1/g' >>$TARGETDIR/metadata.txt
done
cat $TARGETDIR/metadata.txt

