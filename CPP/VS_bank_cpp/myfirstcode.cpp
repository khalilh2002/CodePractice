#include<iostream>
#include<string>
#include<list>
#include<map>
#include<cctype>
#include<fstream>

using namespace std;
std::map <int , string > bank_username; //globale
std::map <int , string > bank_password; //globale
std::map <int , double > bank_balance; //globale

int new_id = (int ) bank_username.size() ;

/*declaration*/
int create_account(string user , string pass );
void login(int id ,string user , string password);
bool check(int id ,string user , string password);
bool check(int id);
void show_balance(int id);
void add_money(int id );
void transfer_from_balance(int id_from ,int id_to , double balance_from );
void transfer_money(int id );
void change_password(int id , string usr , string password);


/*
you should add a data base for the clinet and password
*/

int main(){
    /*test*/
    bank_username[2002]="khalil";
    bank_password[2002]="password";
    bank_balance[2002]=10;

    /*end test*/

    char answer ;
    string user , pass;
    int id ;
    bool good=false ;
    /*data base*/
    ofstream bd ;
    bd.open("data.txt");
    /*end here dont forget to continue*/
    
    cout <<"Hello \nDo you have a bank account ?...(Y/N) :";
    cin >> answer ;
    answer = (char)tolower(answer);

    if (answer=='n')
    {
        cout <<"Do you want to craete a new account ?...(Y/N) :";
        cin >> answer;
        answer = (char)tolower(answer);

        if (answer == 'y')
        {   
            cout <<"give me your username : ";
            cin >> user;
            cout <<"givre me your password : ";
            cin >> pass;
            id = create_account(user,pass);  
            printf("\nyour  id is : %d\n",id); 
        
        }else
        {
            exit(0);
        }

    }else if(answer=='y')
    {
        cout <<"Do you want to log in to your account..(Y/N) : ";
        cin >> answer ;
        answer = (char)tolower(answer);
        if (answer=='y')
        {
            while (!good)
            {
                cout <<"id : ";
                cin >> id;
                cout <<"username : ";
                cin >> user;
                cout <<"password : ";
                cin >> pass;
                good = check(id , user , pass);
            }
            
            login(id , user , pass);
        }
        
    }else
    {
        cout<<"Error the answer is wrong";
    }
    //cout<<"\n your new password "+ bank_username[2002] +"is  : "+bank_password[2002]+"\n\n\n";
    
}

int create_account(string user , string pass ){

    bank_username[new_id]=user;
    bank_password[new_id]=pass;
    bank_balance[new_id]=0.0;

    return new_id++;
}

bool check(int id ,string user , string password){
    if (bank_username[id]==user && bank_password[id]==password)
    {
        cout<<"you log in seccuesfully\n";
        return true;

    }else
    {
        cout<<" Faild \n";
        return false;

    }  
}
//check version 2 for just id
bool check(int id){
    if (bank_username.find(id)==bank_username.end())
    {
        cout<<"the name is "+bank_username[id]+"\n";
        return true;
    }else
    {
        cout<<"the name doesnt exist \n";
        return false;
        
    }
    
}
void login(int id ,string user , string password){
    int choise ;
    cout <<"show balance (1) , add money(2) , transfer money(3) or change password(4)";
    cin >> choise ;
    switch (choise)
    {
    case 1:
        show_balance(id);
        break;
    case 2 :
        add_money(id);
        show_balance(id);
        break;
    case 3:
        show_balance(id);
        transfer_money(id);
        break;
    case 4:
        change_password(id , user , password );
        break;
    default:
        cout<<"ERROR\n";
        break;
    }

}
void show_balance(int id ){
    cout<<"your balance : "<< bank_balance[id] <<" DH";
}
void add_money(int id ){
    double money;
    cout <<"give me the amount : ";
    cin >> money;

    bank_balance[id]+=money;
    cout<<"the money added seccufuly \n";
    
}
void transfer_from_balance(int id_from ,int id_to , double balance_from ){
    double money;
    while (true)
    {
        cout <<"give me the amount : ";
        cin >> money;
        if (money <= balance_from)
        {
            break;
        }
        cout<<"error , you exceed your balance\n";
    }
    
    bank_balance[id_to]+=money;
    bank_balance[id_from]-=money;
    cout<<"the money transfer seccufuly \n";
}

void transfer_money(int id ){
    int id2;
    bool correct=false;
    int choise;
    while (!correct)
    {
        cout<<"the id of the other user : ";
        cin >> id2;
        correct=check(id2);
    }
    /*add fonction to transfe from his account*/
    cout <<" espece(0) or from your account(1) : ";
    cin >> choise;
    switch (choise)
    {
    case 0:
        add_money(id2);
        break;
    case 1:
        transfer_from_balance(id,id2 , bank_balance[id]);
        break;

    default:
        break;
    }
}
void change_password(int id , string usr , string password){
    char answer ;
    string new_password ,new_password2, user_1;

    cout<<"Are you sure you want to change password (Y/N)..";
    cin >> answer;
    answer = (char) tolower(answer);

    switch (answer)
    {
    case 'y' :
        while (true)
        {
            cout << "username : ";
            cin >> user_1;
            cout << "old password : ";
            cin>> new_password;
            if (user_1==usr && new_password==password)
            {
                cout<<"now you can change your password\n";
                break;
            }
            cout<<"somthing is worng check your username or your password\n";
        }
        while (true)
        {
            cout << "new password : ";
            cin>> new_password;
            cout << "confirme new password : ";
            cin>> new_password2;
            if (new_password==new_password2)
            {
                bank_password[id]=new_password;
                cout<<"password changed succufully";
                break;
            }
            cout<<"the password dosnt match try again \n";
        }
        
        break;
    case 'n' :
        cout<<"ok press enter to quit...";
        getchar();
        break;
    default:
        break;
    }
    
}