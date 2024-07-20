from tkinter import *
import random

player="x"
computer="0"


def press_button(row,column):
    global buttons
    if end_game()==False:
        if buttons[row][column]["text"]=='':
            buttons[row][column].config(bg='light gray', text=player, )
        else:
            press_button(row,column)

        while end_game()!=True :
            column_computer,row_computer = random.randint(0,2),random.randint(0,2)
            if buttons[row_computer][column_computer]['text']=='':
                buttons[row_computer][column_computer].config(bg='light gray', text=computer)
                win()
                break

def reset_game():
    global buttons
    show_result("New game")
    for row  in range(3):
        for column in range(3):
            buttons[row][column].config(bg='light gray',text='',)


def empty_space():
    global buttons
    space = 9

    for row  in range(3):
        for column in range(3):
            if buttons[row][column]["text"]!='':
                space-=1
    if space==0:
        show_result("Draw")
        return False
    else:
        return True


def end_game():
    global buttons
    if win() or empty_space()==False:
        return True
    else:
        return False

def show_result(var):
    global label
    if var=='x' or var=='0':
        label.config(text="winner is "+var,fg='light green')
    else:
        label.config(text=var,fg='white')

def win():
    global buttons
    for var in range(3):
        if buttons[var][0]['text']==buttons[var][1]['text']==buttons[var][2]['text']!=''  :

            buttons[var][0].config(bg='light green')
            buttons[var][1].config(bg='light green')
            buttons[var][2].config(bg='light green')
            show_result(buttons[var][0]["text"])
            return True

        elif buttons[0][var]['text']==buttons[1][var]['text']==buttons[2][var]['text']!='':

            buttons[0][var].config(bg='light green')
            buttons[1][var].config(bg='light green')
            buttons[2][var].config(bg='light green')
            show_result(buttons[0][var]["text"])
            return True

    if buttons[0][0]['text']==buttons[1][1]['text']==buttons[2][2]['text']!="":
        buttons[0][0].config(bg='light green')
        buttons[1][1].config(bg='light green')
        buttons[2][2].config(bg='light green')
        show_result(buttons[1][1]["text"])
        return True

    if buttons[0][2]['text']==buttons[1][1]['text']==buttons[2][0]['text']!='':
        buttons[0][2].config(bg='light green')
        buttons[1][1].config(bg='light green')
        buttons[2][0].config(bg='light green')
        show_result(buttons[2][0]["text"])
        return True
    
    return False



buttons=[[0,0,0],
         [0,0,0],
         [0,0,0]]

window = Tk()
window.config(bg='black')
window.title('TIC TAC O')
label=Label(window,text='START',bg='black',fg='white',font=("arial",30,"bold italic"))
label.pack(side=TOP)

frame=Frame(window,bg='black')
frame.pack()

for row in range(3):
    for column in range(3):
        buttons[row][column]=Button(frame,activebackground='sky blue',highlightbackground='black',height=8,width=15,command=lambda x=row,y=column:press_button(x,y))
        buttons[row][column].grid(row=row,column=column)

reset=Button(frame,text='Rest / New game',bg='black',fg='white',height=3,font=("arial",11,'bold'),command=reset_game)
reset.grid(column=1)


window.mainloop()