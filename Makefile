.PHONY: all ensure_bin


all : test

Field : ensure_bin src/Field.java
	javac src/Field.java -d bin

test : ensure_bin test/Field.java Field
	javac src/Field.java test/Field.java -d bin
	java -cp bin/ test.Field

ensure_bin :
	mkdir -p bin
