// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1// eq
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T1
M=M-D;JEQ
M=0
@EQ_F1
0;JMP
(EQ_T1)
M=-1
(EQ_F1)
@SP
M=M+1
// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@16
D=A
@SP
A=M
M=D
@SP
M=M+1// eq
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T2
M=M-D;JEQ
M=0
@EQ_F2
0;JMP
(EQ_T2)
M=-1
(EQ_F2)
@SP
M=M+1
// push constant
@16
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@17
D=A
@SP
A=M
M=D
@SP
M=M+1// eq
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T3
M=M-D;JEQ
M=0
@EQ_F3
0;JMP
(EQ_T3)
M=-1
(EQ_F3)
@SP
M=M+1
// push constant
@892
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1// lt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T4
M=M-D;JLT
M=0
@EQ_F4
0;JMP
(EQ_T4)
M=-1
(EQ_F4)
@SP
M=M+1
// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@892
D=A
@SP
A=M
M=D
@SP
M=M+1// lt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T5
M=M-D;JLT
M=0
@EQ_F5
0;JMP
(EQ_T5)
M=-1
(EQ_F5)
@SP
M=M+1
// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@891
D=A
@SP
A=M
M=D
@SP
M=M+1// lt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T6
M=M-D;JLT
M=0
@EQ_F6
0;JMP
(EQ_T6)
M=-1
(EQ_F6)
@SP
M=M+1
// push constant
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1// gt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T7
M=M-D;JGT
M=0
@EQ_F7
0;JMP
(EQ_T7)
M=-1
(EQ_F7)
@SP
M=M+1
// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1// gt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T8
M=M-D;JGT
M=0
@EQ_F8
0;JMP
(EQ_T8)
M=-1
(EQ_F8)
@SP
M=M+1
// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1// gt
@SP
AM=M-1
D=M
@SP
AM=M-1
@EQ_T9
M=M-D;JGT
M=0
@EQ_F9
0;JMP
(EQ_T9)
M=-1
(EQ_F9)
@SP
M=M+1
// push constant
@57
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@31
D=A
@SP
A=M
M=D
@SP
M=M+1// push constant
@53
D=A
@SP
A=M
M=D
@SP
M=M+1// add
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M+D
@SP
M=M+1// push constant
@112
D=A
@SP
A=M
M=D
@SP
M=M+1// sub
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M-D
@SP
M=M+1// neg
@SP
AM=M-1
D=M
M=-D
@SP
M=M+1// and
@SP
AM=M-1
D=M
@EQ_F10
D;JMP
@SP
AM=M-1
D=M
@EQ_F10
D;JMP
M=-1
@EQ_T10
0;JMP
(EQ_F10)
M=0
(EQ_T10)
@SP
M=M+1
// push constant
@82
D=A
@SP
A=M
M=D
@SP
M=M+1// or
@SP
AM=M-1
D=M
@EQ_T11
D;JLT
@SP
AM=M-1
D=M
@EQ_T11
D;JLT
M=0
@EQ_F11
0;JMP
(EQ_T11)
M=-1
(EQ_F11)
@SP
M=M+1
// not
@SP
AM=M-1
D=M
@EQ_T12
D;JLT
M=0
@EQ_F12
0;JMP
(EQ_T12)
M=-1
(EQ_F12)
@SP
M=M+1
