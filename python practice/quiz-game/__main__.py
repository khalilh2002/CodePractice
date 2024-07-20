import os
import test
questions={'Who is the king  of morocco ?':'B',
           'when morocco got its independce ?':'D',
           'what is the name of the previous king ?':'A',
           'what is the capital of morocco ?':'A'
            }
option=[['A: braham linklon','B: Mohammed 6','C: Mohammed 5','D: Hassan 2'],
        ['A: 1999','B: 1975','C: 1954','D: 1956'],
        ['A: Hassan 2','B: Mohammed 5','C: Moulay Rachid','D: Hassan 3'],
        ['A: Rabat','B: Tanger','C: Casablanca','D: Marrakech']
        ]
x=None
while x!='no':
    test.play_game(questions,option)
    x=None
    while not (x=='yes' or x=='no') :
        x=input('do you want to play again ( yes / no )').lower()

print('byee')