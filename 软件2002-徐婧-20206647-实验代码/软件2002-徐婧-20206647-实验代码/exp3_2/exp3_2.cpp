#include <iostream>
#include <string>
using namespace std;

// ҳ��
typedef struct Page_Table {
    struct Page_Table* next; // ����ָ����һ������ָ��
    int Page_Num; // ҳ��
    int flag; // ��־
    int Block_Num; // ������
    int Modify_Flag; // �޸ı�־
    int place; // �ڴ����ϵ�λ��
}*P_Page; // ָ���ҳ���ָ��

// ��ʼ��ҳ��
void Init_Page(P_Page& H2) {
    H2 = (P_Page)malloc(sizeof(Page_Table)); // ����ͷ���
    H2->next = NULL;
    P_Page p = H2; // ����һ��ָ��
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

// ָ������
typedef struct Instruction {
    struct Instruction* next; // ����ָ����һ���ڵ��ָ��
    char operation[100]; // �����������������ռ�
    int L; // ����ҳ��
    int Unit_Num; // ���嵥Ԫ��
}*P_Ins; // ָ���ָ�����б��ָ��
int Ins_Num; // ��ָ��������

// ��ʼ��ָ�����б�
void Initialization(P_Ins& H1) {
    cout << " ������ָ�������� :";
    cin >> Ins_Num;
    int Num = Ins_Num;
    H1 = (P_Ins)malloc(sizeof(Instruction)); // ����ͷ���
    H1->next = NULL;
    P_Ins p = H1; // ����һ��ָ��
    cout << " ָ��������Ϊ " << Ins_Num << " �������������������Ϣ " << endl;
    cout << endl;
    while (Num--)
    {
        p = p->next = (P_Ins)malloc(sizeof(Instruction));
        cout << " ��������ҳ�ţ���Ԫ�� :";
        cin >> p->operation >> p->L >> p->Unit_Num;
        p->next = NULL;
    }
    p->next = H1->next;
}

//FIFO ҳ�����ģ���㷨
void FIFO(P_Ins& H1, P_Page& H2) {
    cout << endl << "-------------START-----------------\n";
    int p[7], k = 0, Ab_ADD, j, temp; // ����p���飬k�ĳ�ֵ�����Ե�ַ��ȱҳ�ж��е�j����ʱ���� temp
    for (int i = 0; i < 7; i++)
        p[i] = i; // ���� p �ĳ�ʼ��
    P_Ins p1 = H1; p1 = p1->next; // ����һ��ָ�����б��ָ��
    do
    {
        P_Page p2 = H2; p2 = p2->next; // ����һ��ҳ���ָ��
        while (p1->L != p2->Page_Num) { p2 = p2->next; } // ȷ��ȡָ���з��ʵ�ҳ��
        if (p2->flag == 1) // �жϸ�ҵ��־�Ƿ�Ϊ 1
        {
            Ab_ADD = p2->Block_Num * 128 + p1->Unit_Num; // ������Ե�ַ
            if (string(p1->operation) == string(" �� ")) // �ж��Ƿ�Ϊ��ָ��ַ����ıȽ�
            {
                p2->Modify_Flag = 1; // �޸ı�־��Ϊ 1
                cout << " ���Ե�ַΪ�� " << Ab_ADD << endl;
            }
            else cout << " ���Ե�ַΪ�� " << Ab_ADD << endl;
            p1 = p1->next; // ȡ��һ��ָ��
        }
        else
        {
            P_Page p3 = H2; p3 = p3->next; // ����ҳ���һ��ָ�� p3
            j = p[k];
            while (p3->Page_Num != j) { p3 = p3->next; } // ��ָ����� j ҳ
            if (p3->Modify_Flag == 1)
            {
                // ģ��һ�ε�����װ��Ĺ���
                cout << "out" << "" << j << "" << "in" << "" << p1->L << endl;
                p[k] = p1->L; k = (k + 1) % 4;
                p2->Page_Num = p3->Page_Num; p3->Page_Num = p1->L; // ������ҳ
                // �����޸ı�־
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
    } while (p1 != H1->next); // �Ƿ��к��ָ����ж�
    cout << endl;
    cout << " ���� p ��ֵΪ�� ";
    for (int i = 0; i < 7; i++)
        cout << p[i] << " ";
    cout << endl << "-------------END-----------------\n";
}
void main() {
    P_Ins H1; P_Page H2;
    Initialization(H1); // ָ�����г�ʼ��
    Init_Page(H2); // ҳ���ʼ��
    FIFO(H1, H2); // �Ƚ��ȳ�ҳ�����ģ���㷨
}