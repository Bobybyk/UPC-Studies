#include <ostream>

class BoxInt {
    private:
        int valeur;
        static int nbrInstances;
    public:
        BoxInt(int valeur);
        ~BoxInt();
        int getInt();
        void setInt(int valeur);
        static int alive_count();
};

std::ostream& operator << (std::ostream& out, BoxInt &h);