public class CurriculumVitae {
	private final Bac baccalaureat;
	private final Licence licence;
	private final DiplomeInge inge;
	private final Master master;
	private final Doctorat doc;

	public CurriculumVitae(CVBuilder cv) {
		this.baccalaureat = cv.baccalaureat;
		this.licence = cv.licence;
		this.inge = cv.inge;
		this.master = cv.master;
		this.doc = cv.doc;
	}

	public static class CVBuilder {
		private Bac baccalaureat = null;
		private Licence licence = null;
		private DiplomeInge inge = null;
		private Master master = null;
		private Doctorat doc = null;

		public CurriculumVitae build() {
			if (doc != null && master == null) {
				throw new IllegalArgumentException();
			}
			if (master != null && licence == null) {
				throw new IllegalArgumentException();
			}
			if (inge != null && baccalaureat == null) {
				throw new IllegalArgumentException();
			}
			if (licence != null && baccalaureat == null) {
				throw new IllegalArgumentException();
			}
			return new CurriculumVitae(this);
		}	

		public CVBuilder bac(Bac baccalaureat) {
			this.baccalaureat = baccalaureat;
			return this;
		}
		public CVBuilder licence(Licence licence) {
			this.licence = licence;
			return this;
		}
		public CVBuilder inge(DiplomeInge inge) {
			this.inge = inge;
			return this;
		}
		public CVBuilder master(Master master) {
			this.master = master;
			return this;
		}
		public CVBuilder doc(Doctorat doc) {
			this.doc = doc;
			return this;
		}
	}
	public static void main(String[] args) {
		Bac bac = new Bac("S", Diplome.Mention.ASSEZ_BIEN, 2017);
		Licence licence = new Licence("Info", Diplome.Mention.BIEN, 2022);
		CurriculumVitae cv = new CurriculumVitae.CVBuilder().bac(bac).licence(licence).build();
	}
}	