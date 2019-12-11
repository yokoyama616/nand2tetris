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
// function Sys.init 0
(Sys.init)
// push constant 4000	
@4000	
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
@SP
AM=M-1
D=M
@3
M=D
// push constant 5000
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
@SP
AM=M-1
D=M
@4
M=D
// call Sys.main 0
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
@Sys.main
0;JMP
// (RET_ADDRESS_2)
(RET_ADDRESS_2)
// pop temp 1
@SP
AM=M-1
D=M
@6
M=D
// label LOOP
(LOOP)
// goto LOOP
@LOOP
0;JMP
// function Sys.main 5
(Sys.main)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant 4001
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
@SP
AM=M-1
D=M
@3
M=D
// push constant 5001
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
@SP
AM=M-1
D=M
@4
M=D
// push constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 1
@1
D=A
@LCL
D=M+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// push constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 2
@2
D=A
@LCL
D=M+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop local 3
@3
D=A
@LCL
D=M+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// push constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Sys.add12 1
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
@Sys.add12
0;JMP
// (RET_ADDRESS_3)
(RET_ADDRESS_3)
// pop temp 0
@SP
AM=M-1
D=M
@5
M=D
// push local 0
@0
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 1
@1
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 2
@2
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 3
@3
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// push local 4
@4
D=A
@LCL
A=M+D
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
AM=M-1
D=M
@SP
AM=M-1
M=D+M
@SP
M=M+1
// add
@SP
AM=M-1
D=M
@SP
AM=M-1
M=D+M
@SP
M=M+1
// add
@SP
AM=M-1
D=M
@SP
AM=M-1
M=D+M
@SP
M=M+1
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
// function Sys.add12 0
(Sys.add12)
// push constant 4002
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 0
@SP
AM=M-1
D=M
@3
M=D
// push constant 5002
@5002
D=A
@SP
A=M
M=D
@SP
M=M+1
// pop pointer 1
@SP
AM=M-1
D=M
@4
M=D
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
// push constant 12
@12
D=A
@SP
A=M
M=D
@SP
M=M+1
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
