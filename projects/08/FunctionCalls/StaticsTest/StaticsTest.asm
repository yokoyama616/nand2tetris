// init
@256
D=A
@SP
M=D
// call Sys.init 0
// push_return-address
@RET_ADDRESS_1
D=A
@SP
A=M
M=D
@SP
M=M+1
// push_LCL
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_ARG
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP-n-5
@SP
D=M
@5
D=D-A
@0
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Sys.init
0;JMP
// (RET_ADDRESS_1)
(RET_ADDRESS_1)
// function Class1.set 0
(Class1.set)
// push argument 0
@0
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 0
@SP
AM=M-1
D=M
@Class1.0
M=D
// push argument 1
@1
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 1
@SP
AM=M-1
D=M
@Class1.1
M=D
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
// FRAME = LCL
@LCL
D=M
@R13
M=D
// RET = *(FRAME-5)
@5
A=D-A
D=M
@R14
M=D
// *ARG = POP()
@ARG
D=M
@R15
M=D
@SP
AM=M-1
D=M
@R15
A=M
M=D
// SP = ARG + 1
@ARG
D=M
@SP
M=D+1
// THAT = *(FRAME-1)
@R13
AM=M-1
D=M
@THAT
M=D
// THIS = *(FRAME-2)
@R13
AM=M-1
D=M
@THIS
M=D
// ARG = *(FRAME-3)
@R13
AM=M-1
D=M
@ARG
M=D
// LCL = *(FRAME-4)
@R13
AM=M-1
D=M
@LCL
M=D
// goto_RET
@R14
A=M
0;JMP
// function Class1.get 0
(Class1.get)
// push static 0
@Class1.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// push static 1
@Class1.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M-D
@SP
M=M+1
// return
// FRAME = LCL
@LCL
D=M
@R13
M=D
// RET = *(FRAME-5)
@5
A=D-A
D=M
@R14
M=D
// *ARG = POP()
@ARG
D=M
@R15
M=D
@SP
AM=M-1
D=M
@R15
A=M
M=D
// SP = ARG + 1
@ARG
D=M
@SP
M=D+1
// THAT = *(FRAME-1)
@R13
AM=M-1
D=M
@THAT
M=D
// THIS = *(FRAME-2)
@R13
AM=M-1
D=M
@THIS
M=D
// ARG = *(FRAME-3)
@R13
AM=M-1
D=M
@ARG
M=D
// LCL = *(FRAME-4)
@R13
AM=M-1
D=M
@LCL
M=D
// goto_RET
@R14
A=M
0;JMP
// function Class2.set 0
(Class2.set)
// push argument 0
@0
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 0
@SP
AM=M-1
D=M
@Class2.0
M=D
// push argument 1
@1
D=A
@ARG
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// pop static 1
@SP
AM=M-1
D=M
@Class2.1
M=D
// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
// FRAME = LCL
@LCL
D=M
@R13
M=D
// RET = *(FRAME-5)
@5
A=D-A
D=M
@R14
M=D
// *ARG = POP()
@ARG
D=M
@R15
M=D
@SP
AM=M-1
D=M
@R15
A=M
M=D
// SP = ARG + 1
@ARG
D=M
@SP
M=D+1
// THAT = *(FRAME-1)
@R13
AM=M-1
D=M
@THAT
M=D
// THIS = *(FRAME-2)
@R13
AM=M-1
D=M
@THIS
M=D
// ARG = *(FRAME-3)
@R13
AM=M-1
D=M
@ARG
M=D
// LCL = *(FRAME-4)
@R13
AM=M-1
D=M
@LCL
M=D
// goto_RET
@R14
A=M
0;JMP
// function Class2.get 0
(Class2.get)
// push static 0
@Class2.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// push static 1
@Class2.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M-D
@SP
M=M+1
// return
// FRAME = LCL
@LCL
D=M
@R13
M=D
// RET = *(FRAME-5)
@5
A=D-A
D=M
@R14
M=D
// *ARG = POP()
@ARG
D=M
@R15
M=D
@SP
AM=M-1
D=M
@R15
A=M
M=D
// SP = ARG + 1
@ARG
D=M
@SP
M=D+1
// THAT = *(FRAME-1)
@R13
AM=M-1
D=M
@THAT
M=D
// THIS = *(FRAME-2)
@R13
AM=M-1
D=M
@THIS
M=D
// ARG = *(FRAME-3)
@R13
AM=M-1
D=M
@ARG
M=D
// LCL = *(FRAME-4)
@R13
AM=M-1
D=M
@LCL
M=D
// goto_RET
@R14
A=M
0;JMP
// function Sys.init 0
(Sys.init)
// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Class1.set 2
// push_return-address
@RET_ADDRESS_2
D=A
@SP
A=M
M=D
@SP
M=M+1
// push_LCL
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_ARG
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP-n-5
@SP
D=M
@5
D=D-A
@2
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Class1.set
0;JMP
// (RET_ADDRESS_2)
(RET_ADDRESS_2)
// pop temp 0 
@SP
AM=M-1
D=M
@5
M=D
// push constant 23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Class2.set 2
// push_return-address
@RET_ADDRESS_3
D=A
@SP
A=M
M=D
@SP
M=M+1
// push_LCL
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_ARG
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP-n-5
@SP
D=M
@5
D=D-A
@2
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Class2.set
0;JMP
// (RET_ADDRESS_3)
(RET_ADDRESS_3)
// pop temp 0 
@SP
AM=M-1
D=M
@5
M=D
// call Class1.get 0
// push_return-address
@RET_ADDRESS_4
D=A
@SP
A=M
M=D
@SP
M=M+1
// push_LCL
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_ARG
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP-n-5
@SP
D=M
@5
D=D-A
@0
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Class1.get
0;JMP
// (RET_ADDRESS_4)
(RET_ADDRESS_4)
// call Class2.get 0
// push_return-address
@RET_ADDRESS_5
D=A
@SP
A=M
M=D
@SP
M=M+1
// push_LCL
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_ARG
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THIS
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
// push_THAT
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// ARG = SP-n-5
@SP
D=M
@5
D=D-A
@0
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Class2.get
0;JMP
// (RET_ADDRESS_5)
(RET_ADDRESS_5)
// label WHILE
(WHILE)
// goto WHILE
@WHILE
0;JMP
