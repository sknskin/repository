import random

numbers = []
while len(numbers) < 7:

    current_number = int( random.random() * 45 + 1 )

    for number in numbers:
        if current_number == number:
            print("duplicated")
            break
    else: # break 되지 않은 경우
        numbers.append(current_number)

numbers.sort()
print("SELECTED NUMBERS : ", numbers)
