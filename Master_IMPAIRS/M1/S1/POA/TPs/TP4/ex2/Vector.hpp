#include <ostream>

class Vector {
    private:
        int length;
        int* tab;
    public:
        Vector(int nbrValues);
        int getVectorLength();
        ~Vector();
};
std::ostream& operator << (std::ostream& out, Vector &v);