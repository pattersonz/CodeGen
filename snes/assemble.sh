rm main.obj
rm main.smc
./wla-65816 -o main.obj main.asm
./wlalink -v main.link main.smc
zsnes main.smc
