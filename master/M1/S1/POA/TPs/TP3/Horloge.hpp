#include <ostream>

class Horloge {
    private:
        int hours, minutes, seconds;
    public:
        Horloge(int h, int m, int s) : hours{h}, minutes{m}, seconds{s} {};

        int getHours();
        int getMinutes();
        int getSeconds();
        int tick();
};

std::ostream& operator << (std::ostream& out, Horloge &h);