#include <iostream>
#include <string>
using namespace std;

// 页表
typedef struct Page_Table {
    struct Page_Table* next; // 定义指向下一个结点的指针
    int Page_Num; // 页号
    int flag; // 标志
    int Block_Num; // 主存块号
    int Modify_Flag; // 修改标志
    int place; // 在磁盘上的位置
}*P_Page; // 指向该页表的指针

// 初始化页表
void Init_Page(P_Page& H2) {
    H2 = (P_Page)malloc(sizeof(Page_Table)); // 建立头结点
    H2->next = NULL;
    P_Page p = H2; // 定义一个指针
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 0; p->flag = 1; p->Block_Num = 5; p->Modify_Flag = 0; p->place = 011;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 1; p->flag = 1; p->Block_Num = 8; p->Modify_Flag = 0; p->place = 012;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 2; p->flag = 1; p->Block_Num = 9; p->Modify_Flag = 0; p->place = 013;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 3; p->flag = 1; p->Block_Num = 1; p->Modify_Flag = 0; p->place = 021;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 4; p->flag = 0; p->Modify_Flag = 0; p->place = 022;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 5; p->flag = 0; p->Modify_Flag = 0; p->place = 023;
    p = p->next = (P_Page)malloc(sizeof(Page_Table));
    p->Page_Num = 6; p->flag = 0; p->Modify_Flag = 0; p->place = 121;
    p->next = H2->next;
}

// 指令序列
typedef struct Instruction {
    struct Instruction* next; // 定义指向下一个节点的指针
    char operation[100]; // 定义操作名，并分配空间
    int L; // 定义页号
    int Unit_Num; // 定义单元号
}*P_Ins; // 指向该指令序列表的指针
int Ins_Num; // 总指令序列数

// 初始化指令序列表
void Initialization(P_Ins& H1) {
    cout << " 请输入指令序列数 :";
    cin >> Ins_Num;
    int Num = Ins_Num;
    H1 = (P_Ins)malloc(sizeof(Instruction)); // 建立头结点
    H1->next = NULL;
    P_Ins p = H1; // 定义一个指针
    cout << " 指令序列数为 " << Ins_Num << " 个，请依次输入相关信息 " << endl;
    cout << endl;
    while (Num--)
    {
        p = p->next = (P_Ins)malloc(sizeof(Instruction));
        cout << " 操作名，页号，单元号 :";
        cin >> p->operation >> p->L >> p->Unit_Num;
        p->next = NULL;
    }
    p->next = H1->next;
}

//FIFO 页面调度模拟算法
void FIFO(P_Ins& H1, P_Page& H2) {
    cout << endl << "-------------START-----------------\n";
    int p[7], k = 0, Ab_ADD, j, temp; // 定义p数组，k的初值，绝对地址，缺页中断中的j变量时变量 temp
    for (int i = 0; i < 7; i++)
        p[i] = i; // 数组 p 的初始化
    P_Ins p1 = H1; p1 = p1->next; // 定义一个指令序列表的指针
    do
    {
        P_Page p2 = H2; p2 = p2->next; // 定义一个页表的指针
        while (p1->L != p2->Page_Num) { p2 = p2->next; } // 确定取指令中访问的页号
        if (p2->flag == 1) // 判断改业标志是否为 1
        {
            Ab_ADD = p2->Block_Num * 128 + p1->Unit_Num; // 计算绝对地址
            if (string(p1->operation) == string(" 存 ")) // 判断是否为存指令，字符串的比较
            {
                p2->Modify_Flag = 1; // 修改标志置为 1
                cout << " 绝对地址为： " << Ab_ADD << endl;
            }
            else cout << " 绝对地址为： " << Ab_ADD << endl;
            p1 = p1->next; // 取下一条指令
        }
        else
        {
            P_Page p3 = H2; p3 = p3->next; // 定义页表的一个指针 p3
            j = p[k];
            while (p3->Page_Num != j) { p3 = p3->next; } // 用指针代替 j 页
            if (p3->Modify_Flag == 1)
            {
                // 模拟一次调出和装入的过程
                cout << "out" << "" << j << "" << "in" << "" << p1->L << endl;
                p[k] = p1->L; k = (k + 1) % 4;
                p2->Page_Num = p3->Page_Num; p3->Page_Num = p1->L; // 调换两页
                // 调换修改标志
                temp = p3->Modify_Flag; p3->Modify_Flag = p2->Modify_Flag;
                p2->Modify_Flag = temp;
            }
            else
            {
                cout << "in" << "" << p1->L << endl;
                p[k] = p1->L; k = (k + 1) % 4;
                p2->Page_Num = p3->Page_Num; p3->Page_Num = p1->L;
                temp = p3->Modify_Flag; p3->Modify_Flag = p2->Modify_Flag;
                p2->Modify_Flag = temp;
            }
        }
    } while (p1 != H1->next); // 是否有后继指令的判断
    cout << endl;
    cout << " 数组 p 的值为： ";
    for (int i = 0; i < 7; i++)
        cout << p[i] << " ";
    cout << endl << "-------------END-----------------\n";
}
void main() {
    P_Ins H1; P_Page H2;
    Initialization(H1); // 指令序列初始化
    Init_Page(H2); // 页表初始化
    FIFO(H1, H2); // 先进先出页面调度模拟算法
}