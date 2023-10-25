#ifndef _VERTEX
#define _VERTEX

#include <iostream>

class Vertex {
    public:
        Vertex();
        Vertex(std::string label);
        ~Vertex();

        std::string getLabel();
        int getId();
        void setId(int i);
    private:
        std::string label;
        int id;
};

std::ostream& operator<<(std::ostream& out, Vertex &v);

#endif