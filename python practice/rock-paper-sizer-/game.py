import os
import random

choix=['sizer','rock','paper']
answer='Y'
while answer=='Y' :
    x=None
    while x not in choix:
        print('donner le choix :',choix)
        x=input('write here : ').lower()
        if x not in choix :
            print('EROUR write again ..')
    y=random.choice(choix)
    print("computer : {} ".format(y))

    if x==y :
        print("DRAW !!!")
    elif x==choix[0]:
        if y=='rock':
            print("YOU LOSE !!!!")
        elif y=='paper':
            print("YOU WIN !!!!")
    elif x==choix[1]:
        if y=='paper':
            print("YOU LOSE !!!!")
        if y=='sizer':
            print("YOU WIN !!!!")
    elif x==choix[2]:
        if y=='rock':
            print("YOU LOSE !!!!")
        if y=='sizer':
            print("YOU WIN !!!!")
    answer=input("Do you want to play again (Y/N) : ").upper()
