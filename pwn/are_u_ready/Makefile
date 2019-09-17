CC=gcc

CFLAGS=-fno-stack-protector -no-pie 
TARGET=are_u_ready

all: $(TARGET)

$(TARGET): $(TARGET).c
	$(CC) $(CFLAGS) -o $(TARGET) $(TARGET).c

clean:
	$(RM) $(TARGET)

