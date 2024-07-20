#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct mailon
{
    char nom[20];
    int point ;
    struct mailon *suivant;
}mailon;

typedef struct list
{
    mailon *tete;
    mailon *queue;
    int taille;
}list;

list *creat_list(){
    list *l;
    l=(list*)malloc(sizeof(list));
    if(l==NULL){
        printf("error sur list");
        exit(0);
    }
    l->tete=NULL;
    l->queue=NULL;
    l->taille=0; 
    return l;
}

mailon *cree_mailon(){
    mailon *element;
    element=(mailon*)malloc(sizeof(mailon));
    if (element==NULL)
    {
        printf("error sur cree_elment");
        exit(0);
    }
    strcpy(element->nom,"");
    element->point=0;
    
    return element;
}

void remplire_list(list *l , char nom2[20]){
    mailon *new ;
    new = cree_mailon();
    strcpy(new->nom,nom2);
    if (l->taille==0 )
    {
        l->tete=new;
        l->queue=new;
    }else{
        l->queue->suivant=new;
        l->queue=new;
    }
    l->taille++;
    
}

void sort_list(list *l){
    int nb_tmp;
    char nom_tmp[20];
    mailon *courant;

    courant = l->tete;

    for (int i = 0; i < l->taille; i++)
    {
        for (int j = 0; j < l->taille; j++)
        {
            if (courant->point < courant->suivant->point)
            {
                strcpy(nom_tmp,courant->nom);
                nb_tmp = courant->point;

                strcpy(courant->nom,courant->suivant->nom);
                courant->point = courant->suivant->point;

                strcpy(courant->suivant->nom,nom_tmp);
                courant->suivant->point = nb_tmp;

            }
            courant = l->tete;
        }
        
    }
    
}

void add_point(list *l, char eq[20], int p){
    mailon *courant ;
    courant = l->tete;
    int trv=0;
    while (courant != NULL)
    {
        if (strcmp(courant->nom,eq)==0)
        {
            courant->point+=p;
            trv=1;
        }
        
        courant = courant->suivant;
    }
    if (trv==0)
    {
        printf("\nL' EQUIPE N'EXITE PAS !!!!\n");
    }
    
    
}

void match_score(list*l, char eq1[20], int pt1, char eq2[20], int pt2 ){
    if (pt1 > pt2)
    {
        add_point(l,eq1,3);

    }else if (pt1 < pt2)
    {
        add_point(l,eq2,3);

    }else if (pt1 == pt2)
    {
        add_point(l,eq1,1);
        add_point(l,eq2,1);

    }
        
}
//chat gpt help
void displayMatches(list *l) {
    mailon* team1 = l->tete;
    mailon* team2;

    // Iterate over each team
    while (team1 != NULL) {
        team2 = team1->suivant;

        // Compare with remaining teams
        while (team2 != NULL) {
            printf("%s vs %s\n", team1->nom, team2->nom);
            team2 = team2->suivant;
        }

        team1 = team1->suivant;
    }
}

void afficher(list *l){
    mailon *courant;
    courant = l->tete;
    if (l->taille==0)
    {
        printf("list est VID");
    }else{

        while (courant != NULL )
        {
            printf(": %s    : %d \n",courant->nom,courant->point);
            courant = courant->suivant;
        }
        
    }
    
}

int main(){
    list *l;
    l=creat_list();
    int n;
    char nom_equipe[20];
    printf("donner le nombre des equipes : ");
    scanf("%d",&n);
    for (int i = 0; i < n; i++)
    {
        printf("le nom :  ");
        scanf("%s",nom_equipe);
        remplire_list(l,nom_equipe);
    }
    afficher(l);
    char nom_1[20] , nom_2[20];
    int n1 , n2 , end=0 ;

    while(end == 0){

        printf("donner equipe 1 :   ");
        scanf("%s",nom_1);
        printf("les goals de equipe 1 : ");
        scanf("%d",&n1);

        printf("donner equipe 2 :   ");
        scanf("%s",nom_2);
        printf("les goals de equipe 2 : ");
        scanf("%d",&n2);

        match_score(l,nom_1,n1,nom_2,n2);

        printf("\n end(1) continue (0) : )");
        scanf("%d",&end);
    }
    sort_list(l);
    //afficher(l);
    displayMatches(l);
    
    return 0;
}

