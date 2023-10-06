class TimeStamp {
	public:
		int heures, minutes, secondes;
		TimeStamp(int h, int m, int s) : heures{(h+(m+s/60)/60)%24}, minutes{(m+s/60)%60}, secondes{s%60} {};
		void affiche();
};