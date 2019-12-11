// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

@d
M=0

(LOOP)
@8191
D=A
@i
M=D
// scに保存
@SCREEN
D=A
@sc
M=D

(DLOOP)
    // 描画色を取得
    @d
    D=M
    // 取得して色を設定？
    @sc
    A=M
    M=D
    //A=A+1
    @sc
    M=M+1

    @i
    MD=M-1
    @DLOOP
    D;JGT
@KBD
D=M

// goto on
@ON
D;JGT
    // OFF
    @d
    M=0
    @LOOP
    0;JMP

(ON)
@d
M=-1

@LOOP
0;JMP
