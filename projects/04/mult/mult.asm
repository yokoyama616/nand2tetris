// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.

// i=R1
@R1
D=M
@i
M=D

// R2 = 0
@0
D=A
@R2
M=D

(START)
// 終了条件 i <= 0
@i
D=M
@END
D;JLE

// R2 = R2 + R0
@R0
D=M
@R2
M=M+D

// i=i-1
@i
M=M-1

// goto start
@START
0;JMP

// 終了
(END)
@END
0; JMP