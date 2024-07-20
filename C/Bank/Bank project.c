#include<stdio.h>
#include<string.h>      /*verse a mon accont o cree acnt */
#include<stdlib.h>
typedef struct mailon
{
    double val;
    char nom[20],pass[10];
    struct mailon *suivant;

}mailon;
typedef struct list
{
    mailon *tete;
    mailon *queue;
    int taille ;
}list;
mailon *cree_elemnt(){
    mailon *new;
    new=(mailon*)malloc(sizeof(mailon));
    if (new==NULL)
    {
        printf("error sur cree elment ");
        exit(0);
    }
    new->val=0;
    strcpy(new->nom,"");
    strcpy(new->pass,"");

    new->suivant=NULL;

    return new;
}
list *cree_list(){
    list *l;
    l=(list*)malloc(sizeof(list));
    if (l==NULL)
    {
        printf("error cree list");
        exit(0);
    }
    l->tete=NULL;
    l->queue=NULL;
    l->taille=0;

    return l;
}
mailon *remplire_elment(double n , char name[20] ,char password[10]){
    mailon *new;
    new=cree_elemnt();
    if (new==NULL)
    {
        printf("error sur remplire elment");
        exit(0);
    }
    new->val=n;
    strcpy(new->nom,name);
    strcpy(new->pass,password);

    return new;
}
list *remplire_list(list *l , double n ,char name[20],char password[10] ){
    mailon *new;
    new=remplire_elment(n,name,password);
    if (l->taille==0)
    {
        l->tete=new;
        l->queue=new;
    }else
    {
        l->queue->suivant=new;
        l->queue=new;
    }

    l->taille++;

    return l;
}
void afficher(list *l){
    mailon *courant;
    courant=l->tete;
    if (courant==NULL)
    {
        printf("error list vide / aficcher ");

    }else
    {
        while (courant!=NULL)
        {
            printf("%s %s %lf\n",courant->nom,courant->pass,courant->val);
            courant=courant->suivant;
        }

    }
    free(courant);

}
void afficher_file(FILE *f){
    rewind(f);
    char n[20],p[10];
    double d;
    while (feof(f)==NULL)
    {
        fscanf(f,"%s %s %lf",n,p,&d);
        printf("%s %s %lf\n",n,p,d);
    }

}

void copy_list(list *l , FILE *f){
    rewind(f);
    mailon *courant ;
    courant=l->tete;
    if (courant==NULL )
    {
        printf("list vide error");
    }
    while ((courant != NULL))
    {
        fprintf(f,"%s %s %lf\n",courant->nom,courant->pass,courant->val);
        courant=courant->suivant;
    }
    free(courant);
}

int uservrai(FILE *f,char user[20]){
    int n,t;
    double pr;
    t=0;
    char user2[20],bin[10];
    rewind(f);
    while(feof(f)==NULL){
        fscanf(f,"%s %s %lf",&user2,&bin,&pr);
        n=strcmp(user2,user);
        if(n==0){
           t=1;
           break;
        }
    }//1 exit 0 n exit pas
    return t;
}
int pasverai(FILE *f,char pass[10]){
    int n,t;
    double pr;
    t=0;
    char user2[20],bin[10];
    rewind(f);
    while(feof(f)==NULL){
        fscanf(f,"%s %s %lf",&user2,&bin,&pr);
        n=strcmp(bin,pass);
        if(n==0){
           t=1;
           break;
        }
    }//1 exit 0 n exit pas
    return t ;
}
double montant(FILE *f ,char name[20]){
    double n;
    char us[20],ps[10];
    rewind(f);
    while (feof(f)==NULL)
    {
        fscanf(f,"%s %s %lf",us,ps,&n);
        if (strcmp(name,us)==0)
        {
            break;
        }

    }

    return n;
}
void verser(FILE *f,char name[20],double n){
    list *l;
    l=cree_list();
    rewind(f);
    if (l==NULL)
    {
        printf("eror sur list verser");
        exit(0);
    }
    char us[20],ps[10];
    double i;
    while (feof(f)==NULL)
    {
        fscanf(f,"%s %s %lf",us,ps,&i);
        if (strcmp(us,name)==0)
        {
            l=remplire_list(l,i+n,us,ps);
        }else
        {
            l=remplire_list(l,i,us,ps);
        }

    }
    //afficher(l);
    copy_list(l,f);
    //afficher_file(f);
    free(l);
    printf("\n***verser complete");
}

int main(){
    system("color b0");
    FILE *f ;
    char username[20],password[10];
    int x,v;
    int e;
    double mony=0;
    list *l;
    l=cree_list();
    do /*log out and exit code*/
    {
        f=fopen("user_info.txt","r+");
        if (f==NULL)
        {
            printf("error sur ficher");
            exit(0);
        }
        printf("creat account (1) ou entrer accont (2) ou exit(3) : ");
        scanf("%d",&x);
        system("cls");
        if(x==1){
            do{
                printf("username : ");
                scanf("%s",&username);
                printf("password : ");
                scanf("%s",&password);
                v=uservrai(f,username);
                if (v==0)
                {
                    break;
                }
                printf("error le username exit \n");

            }while(v==1);
            f=fopen("user_info.txt","a+");
            fprintf(f,"%s %s %lf\n",username,password,mony);
            /*systeme cls*/
            printf("\ncomplete....pour continue press ENTER");
            getch();
            system("cls");
            printf("est ce que tu veut verser a ton accont oui(1) ou non(2)? : ");
            int i;
            fclose(f);
            scanf("%d",&i);
            system("cls");
            if (i==1)
            {
                double money;
                f=fopen("user_info.txt","r+");
                do
                {
                    printf("donner le montant : ");
                    scanf("%lf",&money);
                    if (money > 0)
                    {
                        break;
                    }
                    printf("error le montant est negative !!!\n");
                } while ( 0 >= money);
                verser(f,username,money);
                int i;
                printf("logou(0) ou exit (1)");
                scanf("%d",&i);
                if(i==0){
                    e=0;
                    continue;
                }else if(i==1){
                    e=1;
                    break;
                }else
                {
                    printf("error");
                }
            }else if (i==2)
            {
                int k=0;
                printf("\nreturn(0) ou exit(1)");
                scanf("%d",&k);
                if (k==0)
                {
                    e=0;
                    continue;
                }else if(k==1)
                {
                    e=1;
                    break;
                }else
                {
                    printf("error");
                }
                
            }
            


        }else if(x==2){
            if(x==2){
                int p=0;
                do{ /*ecrire le nom et pass */
                    printf("username : ");
                    scanf("%s",&username);
                    printf("password : ");
                    scanf("%s",&password);
                    v=uservrai(f,username);
                    p=pasverai(f,password);
                    if(v==0){
                        system("cls");
                        printf("\n username n'exist pas,\n essayer a nouveau\n\n");
                    }
                    if(p==0 && v==1 ){
                        system("cls");
                        printf("\n password est faux,\n essayer a nouveau\n\n");
                    }
                }while(v==0 || p==0);
                /*system cls*/
                printf(" complete....pour continue press ENTER");
                getch();
                system("cls");
                    int v1=0;
                do //retour et exit
                {
                    printf("voir le montant (1) ou transformer (2) ou verser(3) ou logout(4) : ");
                    scanf("%d",&v);
                    system("cls");
                    char user2[20],pas2[10];

                    if (v==1)
                    {
                        printf("le montant est : %lf DH",montant(f,username));
                        printf("\n retour (0) ou exit(1) : ");/*retour et exit*/
                        scanf("%d",&v1);
                        system("cls");
                    }else if (v==2)
                    {
                        double ver,n;
                        char usr[20];
                        int t=0;
                        do/*verfier si username exit*/
                        {
                            printf("donne le userame d arrive ");
                            scanf("%s",usr);
                            t=uservrai(f,usr);
                            if (t==1)
                            {
                                break;
                            }
                            printf("username n'exit pas recrie :********\n ");

                        } while (t!=1);

                        do/*verier le montant exixt dans l'accont*/
                        {
                            printf("donnre le montant pour trnsformer : ");
                            scanf("%lf",&ver);
                            if (montant(f,username) >= ver && ver > 0 )
                            {
                                break;
                            }else if (ver > montant(f,username))
                            {
                                printf("le montant de trasnforeme est plus grand \n ton montant:%lf dh\n",montant(f,username));
                            }else
                            {
                                printf("error le nombre est negative ou '0' , recrire le montat \n");
                            }

                        } while ( (ver > montant(f,username)) || (0 >= ver) );

                        rewind(f);
                        while (feof(f)==NULL)
                        {
                        fscanf(f,"%s %s %lf",user2,pas2,&n);
                        if (strcmp(user2,usr)==0)
                        {
                                l=remplire_list(l,n+ver,user2,pas2);
                        }
                        else if (strcmp(user2,username)==0)
                        {
                                l=remplire_list(l,n-ver,user2,pas2);
                        }
                        else
                        {
                                l=remplire_list(l,n,user2,pas2);
                        }

                        }
                        //afficher(l);
                        copy_list(l,f);
                        printf("transformer est complete : \n le montant qui rest  est : %lf DH",montant(f,username));
                        printf("\n retour (0) ou exit(1) : ");/*retour et exit*/
                        scanf("%d",&v1);
                        system("cls");

                    }else if (v==3)
                    {
                        fclose(f);
                        f=fopen("user_info.txt","r+");
                        if (f==NULL)
                        {
                            printf("error sur le ficher v3 ");
                            exit(0);
                        }
                        double ver;
                        char name[20];
                        v=0;
                        do
                        {
                            printf("username pour verser : ");
                            scanf("%s",name);
                            v=uservrai(f,name);
                            if (v==1)
                            {
                                break;
                            }
                            printf("error username n'exit pas\n");

                        } while (v==0);
                        v=0;
                        do
                        {
                            printf("le montant : ");
                            scanf("%lf",&ver);
                            if ( 0 > ver)
                            {
                                printf("\n*montant negative recrire \n");
                            }

                        } while (0 > ver);

                        verser(f,name,ver);
                        system("cls");
                        printf("complete...");
                        printf("\n retour (0) ou exit(1) : ");/*retour et exit*/
                        scanf("%d",&v1);
                        system("cls");

                    }else if (v==4)/*log out*/
                    {
                        e=0;
                        break;
                    }
                    else
                    {
                        printf("error le choix n'exit pas \n");
                    }
                } while ( v1==0);

            }

        }else if(x==3){
            e=1;
            break;
        }else
        {
            printf("error\n");
            e=0;

        }

        fclose(f);

    } while (e==0);
    printf("exit..");
    getch();

    return 0;
}
