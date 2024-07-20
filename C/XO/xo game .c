#include"stdio.h"
#include"stdlib.h"
#include"time.h"
#include"ctype.h"
#include"string.h"

char board[3][3];
const char computer='0',player='x';

void reset(){
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            board[i][j]=' ';
        }
    }
}

void board2(){
    printf(" %c | %c | %c \n",board[0][0],board[0][1],board[0][2]);
    printf("------------\n");
    printf(" %c | %c | %c \n",board[1][0],board[1][1],board[1][2]);
    printf("------------\n");
    printf(" %c | %c | %c \n",board[2][0],board[2][1],board[2][2]);

}

int free_space(){
    int space=9;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[i][j] != ' ')
            {
                space--;
            }
        }
    }
    return space;

}
void computer_turn(){
    int x,y;
    srand(time(0));
    x=rand() % 3 ;
    y=rand() % 3;
    while (board[x][y]!=' ' && free_space()!=0)
    {
        x=rand() % 3 ;
        y=rand() % 3 ;

        if ( board[x][y]==' ' )
        {
            board[x][y]=computer;
            break;
        }else{
            continue;
        }
    }
    if (free_space()==0)
    {
        printf("end");
    }else if (board[x][y]==' ')
    {
        board[x][y]=computer;
    }


}
char win(){
    int trv=0;
    for (int i = 0; i < 3; i++)
    {
        if (board[i][0]==board[i][1] && board[i][1]==board[i][2]){
            return board[i][0];
            trv=1;
            break;
        }
    }
    for (int i = 0; i < 3; i++)
    {
        if (trv==1){ break;}
        if (board[0][i]==board[1][i] && board[1][i]==board[2][i]){
            trv=1;
            return board[0][i];
            break;
        }
    }
    if (trv==0 && board[0][0]==board[1][1] && board[1][1]==board[2][2] )
    {
        trv=1;
        return board[0][0];
    }else if (trv==0 && board[0][2]==board[1][1] && board[1][1]==board[2][0])
    {
        trv=1;
        return board[0][2];
    }
    if (trv==0)
    {

        return ' ';
    }
}

void player_turn(){
    int x,y;
    while (board[x][y]!=' ' && free_space()!=0)
    {
        printf("colone x : ");
        scanf("%d",&x); x--;
        printf("ligne y : ");
        scanf("%d",&y); y--;
        if (board[x][y]==' ')
        {
            break;
        }
        printf("\nerror \n");
    }
    board[x][y]=player;

}
int main(){
    reset();
    int x;
    int y;
    char winner=' ';
    int end=1 ;
    printf("'Tic Tac Toe (O,X )' a basic game, you play first with X ,\nyou write the column numbre 'X' (1,2,3)and the line numbre Y(1,2,3) to chouse the placement of 'X'\n");
    while (end==1)
    {
       board2();
        do
        {
            player_turn();
            winner=win();
            if (winner!=' ')
            {
                system("cls");
                board2();
                break;
            }
            computer_turn();
            winner=win();
            if (winner!=' ')
            {
                system("cls");
                board2();
               break;
            }
            system("cls");
            board2();
            winner=win();

        } while ( /*winner=' ' &&*/ free_space()!=0);
        printf("\n");

        switch (winner)
            {
            case '0':
                printf("YOU LOSE BITCH !!!!");
                break;
            case 'x':
                printf("YOU WIN !!!!");
                break;
            case ' ':
                printf("DRAW !!!!!!");
                break;
            default:
                printf("error");
                break;
            }
        printf("\n");
        printf("DO YOU WANT TO PLAY AGAIN yes(1) or no(0) : ");
        scanf("%d",&end);
        reset();
        system("cls");
    }

    return 0;
}



