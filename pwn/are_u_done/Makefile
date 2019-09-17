CC=gcc

CFLAGS=-fno-stack-protector -no-pie -static
TARGET=are_u_done

all: $(TARGET)

$(TARGET): $(TARGET).c
	$(CC) $(CFLAGS) -o $(TARGET) $(TARGET).c

clean:
	$(RM) $(TARGET)

