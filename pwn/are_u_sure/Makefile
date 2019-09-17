CC=gcc

CFLAGS=-fno-stack-protector -no-pie -z execstack
TARGET=are_u_sure

all: $(TARGET)

$(TARGET): $(TARGET).c
	$(CC) $(CFLAGS) -o $(TARGET) $(TARGET).c

clean:
	$(RM) $(TARGET)

