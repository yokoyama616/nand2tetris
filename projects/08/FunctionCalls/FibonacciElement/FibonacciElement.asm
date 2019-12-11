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
// function Main.fibonacci 0
(Main.fibonacci)
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
// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
// lt
@SP
AM=M-1
D=M
@SP
AM=M-1
D=M-D
@LT1
D;JLT
@SP
A=M
M=0
@SKIP_LT1
0;JMP
(LT1)
@SP
A=M
M=-1
(SKIP_LT1)
@SP
M=M+1
// if-goto IF_TRUE
@SP
AM=M-1
D=M
@IF_TRUE
D;JNE
// goto IF_FALSE
@IF_FALSE
0;JMP
// label IF_TRUE
(IF_TRUE)
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
// label IF_FALSE
(IF_FALSE)
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
// push constant 2
@2
D=A
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
// call Main.fibonacci 1
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
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Main.fibonacci
0;JMP
// (RET_ADDRESS_2)
(RET_ADDRESS_2)
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
// push constant 1
@1
D=A
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
// call Main.fibonacci 1
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
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Main.fibonacci
0;JMP
// (RET_ADDRESS_3)
(RET_ADDRESS_3)
// add
@SP
AM=M-1
D=M
@SP
AM=M-1
M=D+M
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
// push constant 4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Main.fibonacci 1
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
@1
D=D-A
@ARG
M=D
// LCL = SP
@SP
D=M
@LCL
M=D
// goto_f
@Main.fibonacci
0;JMP
// (RET_ADDRESS_4)
(RET_ADDRESS_4)
// label WHILE
(WHILE)
// goto WHILE
@WHILE
0;JMP
