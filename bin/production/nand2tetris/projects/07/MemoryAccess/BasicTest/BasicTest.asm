
// push constant
@10
D=A
@SP
A=M
M=D
@SP
M=M+1
// local
@0
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
// push constant
@21
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant
@22
D=A
@SP
A=M
M=D
@SP
M=M+1
// argument
@2
D=A
@ARG
M=M+D
@R13
M=D
@SP
AM=M-1
M=D
@R13
A=M
M=D
// argument
@1
D=A
@ARG
M=M+D
@R13
M=D
@SP
AM=M-1
M=D
@R13
A=M
M=D
// push constant
@36
D=A
@SP
A=M
M=D
@SP
M=M+1
// this
@6
D=A
@THIS
M=M+D
@SP
A=M
M=D
@SP
M=M+1
// push constant
@42
D=A
@SP
A=M
M=D
@SP
M=M+1
// push constant
@45
D=A
@SP
A=M
M=D
@SP
M=M+1
// that
@5
D=A
@THAT
M=M+D
@SP
A=M
M=D
@SP
M=M+1
// that
@2
D=A
@THAT
M=M+D
@SP
A=M
M=D
@SP
M=M+1
// push constant
@510
D=A
@SP
A=M
M=D
@SP
M=M+1
// temp
@65
M=D
@SP
A=M
M=D
@SP
M=M+1
// local
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
// that
@5
D=A
@THAT
M=M+D
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
M=M+D
@SP
M=M+1
// argument
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
// sub
@SP
AM=M-1
D=M
@SP
AM=M-1
M=M-D
@SP
M=M+1
// this
@6
D=A
@THIS
M=M+D
@SP
A=M
M=D
@SP
M=M+1
// this
@6
D=A
@THIS
M=M+D
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
M=M+D
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
// temp
@65
M=D
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
M=M+D
@SP
M=M+1