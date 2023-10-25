package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.image.BufferedImage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import model.Complex;
import model.Fractal;
import model.FractalBuilder;
import model.FractalDefinitionDomain;
import model.FractalDesigner;
import model.JuliaFractalList;

public class FractController implements Initializable {

    /*
     * JAVAFX parameters
     */
    @FXML
    private Label imaginaryValue;
    @FXML
    private Button zoomOut;
    @FXML
    private Button createMultipleView;
    @FXML
    private Label x1Value;
    @FXML
    private Label resolution;
    @FXML
    private TextField dimX2;
    @FXML
    private CheckBox feedback;
    @FXML
    private TextField dimX1;
    @FXML
    private TextField nbrView;
    @FXML
    private Label stuff6;
    @FXML
    private Label y2Value;
    @FXML
    private Label stuff7;
    @FXML
    private Label stuff4;
    @FXML
    private TextField gap;
    @FXML
    private Label stuff5;
    @FXML
    private Button createFract;
    @FXML
    private Label y1Value;
    @FXML
    private Label zoomValue;
    @FXML
    private Label gapValue;
    @FXML
    private Button zoomIn;
    @FXML
    private Label stuff2;
    @FXML
    private Label stuff3;
    @FXML
    private TextField real;
    @FXML
    private Label stuff1;
    @FXML
    private TextField zoomCoef;
    @FXML
    private Label x2Value;
    @FXML
    private TextField dimY1;
    @FXML
    private Label realValue;
    @FXML
    private TextField dimY2;
    @FXML
    private TextField imaginary;
    @FXML
    private ImageView fractalView;
    @FXML
    private Button up;
    @FXML
    private Button left;
    @FXML
    private Button right;
    @FXML
    private Button down;
    @FXML
    private Button cam;
    @FXML
    private TextField shiftingCoef;
    @FXML
    private ComboBox<String> fractalComboBox;
    
    // liste des labels affichés sur la preview de l'interface
    private ArrayList<Node> feedbackComponentList = new ArrayList<Node>();
    // état de l'affichage du feedback
    private boolean feedbackState = true;
    // nombre identifiant des captures de l'image fractale
    private int nbrScreenshort = 1;
    // Objet fractale
    private Fractal f;
    // Buffer stockant les octets à écrire dans un fichier ou dans la preview
    private BufferedImage buffImg;
    

    // méthode pour convertir les champs de l'interface en double pour traitement
    private double fieldToDouble(TextField field) {
        return Double.parseDouble(field.getText());
    }


    /* 
     * méthode pour appeler la suite de fonctions pour
     *  - générer la fractale
     *  - l'écrire dans un buffer
     *  - écrire le buffer dans la preview de l'interface et dans un fichier
     */
    @FXML
    void generateFractal(ActionEvent event) {

        if(isFractalMenuFilled()) {

            f = new FractalBuilder().setDefinitionDomain(new FractalDefinitionDomain(
                fieldToDouble(dimX1), 
                fieldToDouble(dimX2), 
                fieldToDouble(dimY1), 
                fieldToDouble(dimY2)))
                .setGap(fieldToDouble(gap))
                .setComplexConstant(new Complex(
                    fieldToDouble(real), 
                    fieldToDouble(imaginary)))
                    .buildJulia();
            
            FractalDesigner designer = new FractalDesigner(f);
            buffImg = designer.drawFractal();
            designer.writeImage(buffImg);
            
            Image image = convertToFxImage(buffImg);
            fractalView.setImage(image);
            
            realValue.setText(real.getText());
            imaginaryValue.setText(imaginary.getText());
            gapValue.setText(gap.getText());
            x1Value.setText(dimX1.getText());
            x2Value.setText(dimX2.getText());
            y1Value.setText(dimY1.getText());
            y2Value.setText(dimY2.getText());
            zoomValue.setText(zoomCoef.getText());

            resolution.setText((int)(fieldToDouble(dimX2)-fieldToDouble(dimX1)/fieldToDouble(gap))
            + " * " 
            + ((int)(fieldToDouble(dimY2))-(fieldToDouble(dimY1)))/fieldToDouble(gap));
        }
    }

    // convertion de BufferedImage en un format lisible par Image
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage write = null;
        if (image != null) {
            write = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = write.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        return new ImageView(write).getImage();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // initialisation des champs avec des valeurs par défaut
        real.setText("-0.7269");
    	imaginary.setText("0.1889");
        dimX1.setText("-1");
        dimX2.setText("1");
        dimY1.setText("-1");
        dimY2.setText("1");
        gap.setText("0.002");
        zoomCoef.setText("2");
        shiftingCoef.setText("2");

        // Ajout des labels de la preview dans une liste pour une meilleure manipulation
        feedbackComponentList.add(realValue);
        feedbackComponentList.add(imaginaryValue);
        feedbackComponentList.add(gapValue);
        feedbackComponentList.add(x1Value);
        feedbackComponentList.add(x2Value);
        feedbackComponentList.add(y1Value);
        feedbackComponentList.add(y2Value);
        feedbackComponentList.add(zoomValue);
        feedbackComponentList.add(stuff1);
        feedbackComponentList.add(stuff2);
        feedbackComponentList.add(stuff3);
        feedbackComponentList.add(stuff4);
        feedbackComponentList.add(stuff5);
        feedbackComponentList.add(stuff6);
        feedbackComponentList.add(stuff7);
        feedbackComponentList.add(resolution);

        // ajout des éléments de la classe enum JuliaFractalList dans un menu déroulant ComboBox
        List<JuliaFractalList> list = (Arrays.asList(JuliaFractalList.values()));
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0 ; i<list.size() ; i++) {
            strList.add(list.get(i).toString());
        }
        fractalComboBox.getItems().setAll(strList);
    }

    
    // gestion de l'affichage des labels sur la preview
    @FXML
    void checkFeedbackState(ActionEvent event) {
        if (feedbackState) {
            for (int i = 0 ; i<feedbackComponentList.size() ; i++) {
                feedbackComponentList.get(i).setVisible(false);
                feedbackState = false;
            }
        } else {
            for (int i = 0 ; i<feedbackComponentList.size() ; i++) {
                feedbackComponentList.get(i).setVisible(true);
                feedbackState = true;
            }   
        }
    }


    /*
     * méthode pour s'occuper de la génération 
     * d'une fractale avec plusieurs niveaux de zoom 
     */ 
    @FXML
    void generateMultipleView(ActionEvent event) {
        if(isFractalMenuFilled() && isZoomFilled() && isNbrOfViewFilled()) {
            int nbrView = Integer.parseInt(this.nbrView.getText());

            for (int i = 0 ; i<nbrView ; i++) {
                f = new FractalBuilder().setDefinitionDomain(new FractalDefinitionDomain(
                    fieldToDouble(dimX1), 
                    fieldToDouble(dimX2), 
                    fieldToDouble(dimY1), 
                    fieldToDouble(dimY2)))
                    .setGap(fieldToDouble(gap))
                    .setComplexConstant(new Complex(
                        fieldToDouble(real), 
                        fieldToDouble(imaginary)))
                        .buildJulia();
                
                FractalDesigner designer = new FractalDesigner(f, (i+1));
                buffImg = designer.drawFractal();
                designer.writeImage(buffImg);
                Image image = convertToFxImage(buffImg);
                fractalView.setImage(image);
        
                realValue.setText(real.getText());
                imaginaryValue.setText(imaginary.getText());
                gapValue.setText(gap.getText());
                x1Value.setText(dimX1.getText());
                x2Value.setText(dimX2.getText());
                y1Value.setText(dimY1.getText());
                y2Value.setText(dimY2.getText());
                zoomValue.setText(zoomCoef.getText());
                
                resolution.setText((int)(fieldToDouble(dimX2)-fieldToDouble(dimX1)/fieldToDouble(gap))
                + " * " 
                + ((int)(fieldToDouble(dimY2)-(fieldToDouble(dimY1))/fieldToDouble(gap)))); 
                
                zoomIn(event);   
            }
        }
    }


    /* méthode pour récupérer les données 
     * dans enum de la fractale sélectionnée 
     */
    @FXML
    void loadFractalEnum(ActionEvent event) {
        JuliaFractalList enumVal = JuliaFractalList.valueOf(fractalComboBox.getValue());
        real.setText(Double.toString(enumVal.getReal()));
        imaginary.setText(Double.toString(enumVal.getImaginary()));
        gap.setText(Double.toString(enumVal.getGap()));
        dimX1.setText(Double.toString(enumVal.getX1()));
        dimX2.setText(Double.toString(enumVal.getX2()));
        dimY1.setText(Double.toString(enumVal.getY1()));
        dimY2.setText(Double.toString(enumVal.getY2()));
    }

    // sauvegarde de la preview sous forme de .png
    @FXML
    void screenshot(ActionEvent event) {
        if (f != null && buffImg != null) {
            FractalDesigner designer = new FractalDesigner(f, "screenshot", nbrScreenshort);
            nbrScreenshort+=1;
            designer.writeImage(buffImg);
        } else {
            System.out.println("fractal not created");
        }
    }


    // zoom dans la fractale +
    @FXML
    void zoomIn(ActionEvent event) {
        if (isZoomFilled()) {
            gap.setText(Double.toString(fieldToDouble(gap)/fieldToDouble(zoomCoef)));
            dimX1.setText(Double.toString(fieldToDouble(dimX1)/fieldToDouble(zoomCoef)));
            dimX2.setText(Double.toString(fieldToDouble(dimX2)/fieldToDouble(zoomCoef)));
            dimY1.setText(Double.toString(fieldToDouble(dimY1)/fieldToDouble(zoomCoef)));
            dimY2.setText(Double.toString(fieldToDouble(dimY2)/fieldToDouble(zoomCoef)));
            generateFractal(event);
        }
    }
    // zoom dans la fractale -
    @FXML
    void zoomOut(ActionEvent event) {
        if (isZoomFilled()) {
            gap.setText(Double.toString(fieldToDouble(gap)*fieldToDouble(zoomCoef)));
            dimX1.setText(Double.toString(fieldToDouble(dimX1)*fieldToDouble(zoomCoef)));
            dimX2.setText(Double.toString(fieldToDouble(dimX2)*fieldToDouble(zoomCoef)));
            dimY1.setText(Double.toString(fieldToDouble(dimY1)*fieldToDouble(zoomCoef)));
            dimY2.setText(Double.toString(fieldToDouble(dimY2)*fieldToDouble(zoomCoef)));
            generateFractal(event);
        }
    }


    // vérification du bon remplissage des champs
    private boolean isFractalMenuFilled() {
        if(imaginary.getText().trim().isEmpty() 
        || real.getText().trim().isEmpty()
        || dimX2.getText().trim().isEmpty()
        || dimX2.getText().trim().isEmpty()
        || dimY1.getText().trim().isEmpty()
        || dimY2.getText().trim().isEmpty()
        || gap.getText().trim().isEmpty()) {
            System.out.println("Some fields of fractal menu are empty, please check");
            return false;
        } else {
            return true;
        }
    }
    // vérification du bon remplissage des champs
    private boolean isZoomFilled() {
        if (zoomCoef.getText().trim().isEmpty()) {
            System.out.println("zoom coef is empty, please check");
            return false;
        } else {
            return true;
        }
    }
    // vérification du bon remplissage des champs
    private boolean isNbrOfViewFilled() {
        if (nbrView.getText().trim().isEmpty()) {
            System.out.println("number of view is empty, please check");
            return false;
        } else {
            return true;
        }    
    }
    // vérification du bon remplissage des champs
    private boolean isShiftingCoefFilled() {
        if (shiftingCoef.getText().trim().isEmpty()) {
            System.out.println("shifting coef is empty, please check");
            return false;
        } else {
            return true;
        }
    }


    // déplacement dans la fractale
    @FXML
    void shiftUp(ActionEvent event) {
        if (isFractalMenuFilled() && isShiftingCoefFilled()) {
            dimY1.setText(Double.toString(fieldToDouble(dimY1)-fieldToDouble(shiftingCoef)));
            dimY2.setText(Double.toString(fieldToDouble(dimY2)-fieldToDouble(shiftingCoef)));
            generateFractal(event);
        }
    }
    @FXML
    void shiftLeft(ActionEvent event) {
        if (isFractalMenuFilled() && isShiftingCoefFilled()) {
            dimX1.setText(Double.toString(fieldToDouble(dimX1)-fieldToDouble(shiftingCoef)));
            dimX2.setText(Double.toString(fieldToDouble(dimX2)-fieldToDouble(shiftingCoef)));
            generateFractal(event);
        }
    }
    @FXML
    void shiftRight(ActionEvent event) {
        if (isFractalMenuFilled() && isShiftingCoefFilled()) {
            dimX1.setText(Double.toString(fieldToDouble(dimX1)+fieldToDouble(shiftingCoef)));
            dimX2.setText(Double.toString(fieldToDouble(dimX2)+fieldToDouble(shiftingCoef)));
            generateFractal(event);
        }
    }
    @FXML
    void shiftDown(ActionEvent event) {
        if (isFractalMenuFilled() && isShiftingCoefFilled()) {
            dimY1.setText(Double.toString(fieldToDouble(dimY1)+fieldToDouble(shiftingCoef)));
            dimY2.setText(Double.toString(fieldToDouble(dimY2)+fieldToDouble(shiftingCoef)));
            generateFractal(event);
        }
    }

}
