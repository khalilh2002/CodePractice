def play_game(questions, options):
    c = 0
    answer = []
    for key in questions:
        print("-------------------------------")
        print(key)
        for j in options[c]:
            print(j, end='\t\t')
        print()
        x = input("answer here : ").upper()
        answer.append(x)
        c += 1
    score = check(questions, answer)
    print('-------------------------------------------------')
    print('RESULT!!!!!!!!')
    print('your answer : \t', end=' ')
    for i in answer:
        print(i,end=' ')
    print()
    print('correct answer :', end=' ')
    for i in questions.values():
        print(i, end=' ')
    print('\nyour score is {} % '.format(int(score)))


def check(question, answer):
    i = 0
    score = 0
    for j in question.values():
        if j == answer[i]:
            score += 1
        i += 1
    score = score/len(question)*100
    return score
