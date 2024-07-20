#include<stdio.h>
#include <math.h>
#define PI 3.141592654

int i , j , k ;

int main (){
    int choix,y;
    int  n , m ;
    double a,b,x;

    printf("le mode de calcule \n\t 1 : normal \n\t 2 : matrice \n\t 3 : cos/sin/tan  \n choix : ");
    scanf("%d",&choix);
    printf("\n------------------------------------------\n");
    if (choix == 1) /*normal*/
    {
         do{     /*les calcule normal*/
        printf("\n------------------------------------------");/* les operation*/

        printf("\n donner le operatio: \n\t 1:multyplication\n\t 2:somme\n\t 3:devision\n\t 4:soustraction\n      le choix : ");                                       /*le operation*/
            scanf("%d",&choix);
            while (choix < 1 || choix > 4)
            {
                printf("\n EROR  \n ecrire un nombre dans les choix :  ");
                scanf("%d",&choix);
            }
        printf("\n------------------------------------------");

            printf("\n nombre 1 = "); //les nombres a et b
            scanf("%lf",&a);
            printf("\n nombre 2 = ");
            scanf("%lf",&b);

        printf("\n------------------------------------------\n");

        switch (choix)
        {
        case 1: //multyplication
            x=a*b ;
            printf("\n %0.2lf x %0.2lf = %lf",a,b,x);

            break;
        case 2 : //somme
            x=a+b;
            printf("\n %0.2lf + %0.2lf = %0.2lf",a,b,x);
            break;
        case 3 : //devision
            x=a/b;
            printf("\n %0.2lf / %0.2lf = %0.2lf ",a,b,x);
            break;
        case 4 : //moins
            x=a-b;
            printf("\n %0.2lf - %0.2lf = %0.2lf",a,b,x);
            break;
        default:
            printf("eroor");
            break; /*end of the switch calcltar*/
        }
        printf("\n__________________________________________________");

            printf("\n do you want to exit Y[1]  or N[2] ");
            scanf("%d",&y);

        }while(  y == 2 );

    }else {
        if (choix == 2) /*matrice*/
    {
        printf("\n 1 : la somme matriciel \n 2 : le produit matriecile \n\n le choix : "); /*le choix de matrice*/
        scanf("%d",&choix);

        printf("------------------------------------------\n");

        int p1 , p2   ;

        printf("A[n][p1] et B[p2][m]  | \n                      |\n");
        printf("______________________\n");
        do
        {
        printf("\nla taille de A \n");   //A

        printf("\n donner n = ");
        scanf("%d",&n);
        printf("\n donner p1 = ");
        scanf("%d",&p1);
             printf("\n______________________\n");
        printf("\nla taille de B \n ");   //B

        printf("\n donner p2 = ");
        scanf("%d",&p2);
        printf("\n donner m = ");
        scanf("%d",&m);

        if (choix == 1 && n==p2 && m==p1)
        {
           break;
        }
        if (choix ==2 && p2 == p1 )
        {
            break;
        }

        printf("\n error");
        printf("-------------------------------------------\n");
        } while ( 2);

        int a[n][p1] , b[p2][m] ,c[n][m];

        printf("\n------------------------------------------\n");
        switch (choix)
        {
        case 1 :
            // la somme de matrics

            printf("------------------------------------------\n"); ;

                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < p1 ; j++)
                    {
                        printf("A[%d][%d] = ",i+1,j+1);
                        scanf("%d",&a[i][j]);
                    }
                }
                printf("\n");

                for ( i = 0; i < p2; i++)
                    {
                    for ( j = 0; j < m ; j++)
                        {
                            printf("B[%d][%d] = ",i+1,j+1);
                            scanf("%d",&b[i][j]);
                        }
                    }
                    printf("\n");
                //somme
                for (i = 0; i < n; i++)
                {
                   for (j = 0; j < m; j++)
                   {
                       c[i][j]=a[i][j]+b[i][j];
                   }

                }
                    printf("\n");
                printf("------------------------------------------\n");
                for (int i = 0; i < n; i++)
                    {
                    for (int j = 0; j < m; j++)
                        {
                        printf("\t s[%d][%d]= %d",i,j, c[i][j]);
                    }
                    printf("\n");
                }
               printf("------------------------------------------\n");
            break;

        case 2 :

            // le produit  de matrics

            printf("------------------------------------------\n");

                for (int i = 0; i < n; i++)
                {
                    for (int j = 0; j < p1 ; j++)
                    {
                        printf("A[%d][%d] = ",i+1,j+1);
                        scanf("%d",&a[i][j]);
                    }
                }
                printf("\n");

                for (int i = 0; i < p1; i++)
                    {
                    for (int j = 0; j < m ; j++)
                        {
                            printf("B[%d][%d] = ",i+1,j+1);
                            scanf("%d",&b[i][j]);
                        }

                    }
                    printf("\n");


                for ( i = 0; i < n; i++)
                {
                    for ( j = 0; j < m; j++)
                    {
                        c[i][j]=0;

                       for ( k = 0; k < p1; k++)
                       {
                           c[i][j] += a[i][k] * b[k][j];
                       }
                    }
                }
                printf("------------------------------------------\n");
                for (int i = 0; i < n; i++)
                    {
                    for (int j = 0; j < m; j++)
                        {
                        printf("\t p[%d][%d]= %d",i,j, c[i][j]);
                    }
                    printf("\n");
                }
               printf("------------------------------------------\n");
            break ;
        default :
            printf("\n error");
            break;
        }
    }
    if (choix == 3) /*cos / sin / tan / arc c s t */
    {
        double arg, numb , r;
        printf("\t 1:cos \n\t 2:sin \n\t 3:tan \n\t 4:cos^(-1)/sin^(-1)/tan^(-1) \n le choix : ");
        scanf("%d",&choix);
        printf("\n------------------------------------------\n");
        printf("\n donner (degree ) x = ");
        scanf("%lf",&arg);

        printf("\n------------------------------------------\n");

        numb = arg ; //cos et nombre

        // Converting to radian
        arg = (arg * PI) / 180;

        printf("\n");
        switch (choix)
        {
        case 1:
            r = cos(arg);

            printf(" cos(x) = %.2lf",r);

            break;

        case 2 :
            r = sin(arg);

            printf(" sin(x) = %.2lf",r);

            break;
        case 3 :
            r = tan(arg);

            printf(" tan(x) = %.2lf",r);

            break;
        case 4 :

            printf("\t 1:cos^(-1) \n\t 2:sin^(-1) \n\t 3:tan^(-1) \n le choix = "); /* arccos et arctan et arcsin   */
            scanf("%d",&choix);
            printf("\n------------------------------------------\n");
                switch (choix)
                {
                case 1:
                    r = acos(numb);
                    r = (r * 180 )/ PI ;
                    printf("\n cos ^(-1 ) = %.2lf degree", r );
                    break;
                case 2 :
                    r = asin(numb);
                    r = (r * 180 )/ PI ;
                    printf("\n sin ^(-1 ) = %.2lf degree", r );
                    break;
                case 3 :
                    r = atan(numb);
                    r = (r * 180 )/ PI ;
                    printf("\n tan ^(-1 ) = %.2lf degree", r );
                    break;

                default:
                    break;
                }

            break;
        default:
            printf("\n error");
            break;
        }
        printf("\n");
        printf("\n------------------------------------------\n");
    }
    
}
return 0;
}
    