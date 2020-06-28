/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.Movie;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import data.DataReader;
import data.UserRatings;
import java.util.Random;
import java.util.TreeMap;
import rs.Recommender;

/**
 * Main graphic app to use the Recommendation System.
 *
 * @author Lidia Sánchez Mérida.
 */
public class MainWindow extends javax.swing.JFrame {
    /**
     * Number of movies which will be shown to the user.
     */
    private static final int NMOVIES = 20;
    /**
     * Number of neighbours.
     */
    private static final int K = 10;
    /**
     * Path to the ratings data file.
     */
    private static String ratingsPath = ""; //= "/home/lidia/Documentos/RS/u.data";
    /**
     * Path to the movies data file.
     */
    private static String moviesPath = ""; //= "/home/lidia/Documentos/RS/u.item";
    /**
     * Filter to choose the ratings file.
     */
    private final FileNameExtensionFilter ratingsFileFilter =
        new FileNameExtensionFilter("Ratings file [data]", "data");
    /**
     * Filter to choose the movies file.
     */
    private final FileNameExtensionFilter moviesFileFilter =
        new FileNameExtensionFilter("Movies file [item]", "item");
    /**
     * Reader which reads the ratings and movies files.
     */
    private static DataReader reader;
    /**
     * Rating of the movies showed.
     */
    private UserRatings userRatings;
    /**
     * Selected movie to rate.
     */
    private int selectedMovie = -1;
    /**
     * Recommender.
     */
    private Recommender recommender;
    /**
     * Similarity between the active user and the others. It's ordered by the
     * similarity.
     */
    private TreeMap<Double, Integer> similarity;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        this.setTitle("Recommendation System");
        // Full screen
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    /**
     * Dialog to choose a text files.
     * It will be used to set the ratings data file and the movies data file.
     * @param type V if it's the ratings file, M if it's the movies file.
     * @return
     */
    private String chooseFile(String type) {
        String path = "";
        // Dialog to choose the directory
        JFileChooser dlg = new JFileChooser();
        // Extension filters
        if ("R".equals(type)) dlg.setFileFilter(ratingsFileFilter);
        else if ("M".equals(type)) dlg.setFileFilter(moviesFileFilter);
        // Open the dialog
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            // Get the selected stop words file
            File file = dlg.getSelectedFile();
            path = file.toString();
            // Check the file extension
            int point = file.getName().lastIndexOf('.');
            String extension = file.getName().substring(point+1);
            if ("R".equals(type)) {
                if (ratingsFileFilter.toString().contains(extension)) path = file.toString();
            }
            else if ("M".equals(type)) {
                if (moviesFileFilter.toString().contains(extension)) path = file.toString();
            }
        }
        return path;
    }
    /**
     * Reads the ratings and movies files. Then it shows 20 movies randomly
     * to the user to be ratings by them.
     * @throws IOException
     */
    private void readData() throws IOException {
        if (!"".equals(ratingsPath) && !"".equals(moviesPath)) {
            reader = new DataReader(ratingsPath, moviesPath);
            reader.readMoviesData();
            reader.readRatingsData();
            showMoviesRandomly();
        }
    }
    /**
     * Shows 20 movies randomly to be rated by the user.
     * @param movies all the movies.
     * @param nMovies movies to show.
     */
    private void showMoviesRandomly() {
        // Shuffle all movies
        List<Map.Entry<Integer, String>> moviesList =
            new ArrayList<Map.Entry<Integer, String>>(reader.getMovies().entrySet());
        Collections.shuffle(moviesList);
        // Model
        DefaultListModel moviesModel = new DefaultListModel();
        // Gets only 20 movies and set their ratings randomly
        Random rand = new Random();
        ArrayList<Movie> moviesToShow = new ArrayList();
        for (Map.Entry<Integer, String> movie: moviesList.subList(0, NMOVIES)) {
            int randomRating = rand.nextInt(5) + 1;
            moviesToShow.add(new Movie(movie.getKey(), movie.getValue(), randomRating));
            moviesModel.addElement("  " +randomRating + "* " + movie.getValue());
        }
        // Current ratings of the user
        userRatings = new UserRatings(-1, moviesToShow);
        this.ratingMoviesList.setModel(moviesModel);
    }
    /**
     * Updates the view of the movies to rate.
     */
    private void updateMoviesList() {
        DefaultListModel moviesList = new DefaultListModel();
        for (int i=0; i<userRatings.getRatings().size(); i++) {
            moviesList.add(i, "  " +userRatings.getRatings().get(i).getRating()
                + "* "+ userRatings.getRatings().get(i).getTitle());
        }
        this.ratingMoviesList.setModel(moviesList);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aboutDialog = new javax.swing.JDialog();
        aboutScreen = new javax.swing.JPanel();
        helpTitle = new javax.swing.JLabel();
        help12 = new javax.swing.JLabel();
        help13 = new javax.swing.JLabel();
        help14 = new javax.swing.JLabel();
        help15 = new javax.swing.JLabel();
        helpOption2 = new javax.swing.JLabel();
        helpOption1 = new javax.swing.JLabel();
        help11 = new javax.swing.JLabel();
        help16 = new javax.swing.JLabel();
        help17 = new javax.swing.JLabel();
        ratingDialog = new javax.swing.JDialog();
        ratingPanel = new javax.swing.JPanel();
        labelMovie1 = new javax.swing.JLabel();
        labelMovieTitle1 = new javax.swing.JLabel();
        ratingLabel = new javax.swing.JLabel();
        starsCB1 = new javax.swing.JComboBox<>();
        rateButton = new javax.swing.JButton();
        lowerToolbar = new javax.swing.JPanel();
        stateToolbar = new javax.swing.JLabel();
        outputPanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        scrollMovieList = new javax.swing.JScrollPane();
        ratingMoviesList = new javax.swing.JList<>();
        scrollRecommendations = new javax.swing.JScrollPane();
        recommendedMoviesList = new javax.swing.JList<>();
        menu = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        setRatingsFile = new javax.swing.JMenuItem();
        setMoviesFile = new javax.swing.JMenuItem();
        moviesMenu = new javax.swing.JMenu();
        suggestMovies = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutOption = new javax.swing.JMenuItem();

        aboutDialog.setAlwaysOnTop(true);
        aboutDialog.setMinimumSize(new java.awt.Dimension(400, 350));
        aboutDialog.setModal(true);

        aboutScreen.setBackground(new java.awt.Color(234, 234, 234));

        helpTitle.setFont(new java.awt.Font("aakar", 1, 20)); // NOI18N
        helpTitle.setForeground(new java.awt.Color(92, 92, 92));
        helpTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        helpTitle.setText("USER MANUAL");

        help12.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help12.setForeground(new java.awt.Color(109, 109, 120));
        help12.setText("2. Select the directory which contains the ratings data.");

        help13.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help13.setForeground(new java.awt.Color(109, 109, 120));
        help13.setText("3. If everything went well, it will show 20 movies randomly along with their ratings.");

        help14.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help14.setForeground(new java.awt.Color(109, 109, 120));
        help14.setText("4. You can change de rating by clicking on the movie.");

        help15.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help15.setForeground(new java.awt.Color(109, 109, 120));
        help15.setText("5. If you do that, it will show a dialog window with current rating to change it.");

        helpOption2.setFont(new java.awt.Font("aakar", 1, 18)); // NOI18N
        helpOption2.setForeground(new java.awt.Color(109, 109, 120));
        helpOption2.setText("Suggest movies");

        helpOption1.setFont(new java.awt.Font("aakar", 1, 18)); // NOI18N
        helpOption1.setForeground(new java.awt.Color(109, 109, 120));
        helpOption1.setText("Read data");

        help11.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help11.setForeground(new java.awt.Color(109, 109, 120));
        help11.setText("1. Select the folder which contains the movies data.");

        help16.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help16.setForeground(new java.awt.Color(109, 109, 120));
        help16.setText("1. Click on Movies > Suggest movies.");

        help17.setFont(new java.awt.Font("aakar", 0, 18)); // NOI18N
        help17.setForeground(new java.awt.Color(109, 109, 120));
        help17.setText("2. If everything went well, on the right side of the window the suggested movies will appear.");

        javax.swing.GroupLayout aboutScreenLayout = new javax.swing.GroupLayout(aboutScreen);
        aboutScreen.setLayout(aboutScreenLayout);
        aboutScreenLayout.setHorizontalGroup(
            aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutScreenLayout.createSequentialGroup()
                .addGroup(aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aboutScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(helpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(helpOption1)
                            .addComponent(helpOption2)
                            .addGroup(aboutScreenLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(help11)
                                    .addComponent(help12)
                                    .addComponent(help13)
                                    .addComponent(help14)
                                    .addComponent(help15)))))
                    .addGroup(aboutScreenLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(help16)
                            .addComponent(help17))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutScreenLayout.setVerticalGroup(
            aboutScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helpTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helpOption1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(helpOption2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(help17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        aboutDialog.getContentPane().add(aboutScreen, java.awt.BorderLayout.CENTER);

        ratingPanel.setBackground(new java.awt.Color(254, 254, 254));

        labelMovie1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        labelMovie1.setText("Movie");

        labelMovieTitle1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N

        ratingLabel.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        ratingLabel.setText("Rating");

        starsCB1.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        starsCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1*", "2**", "3**", "4**", "5**" }));

        rateButton.setBackground(new java.awt.Color(85, 99, 235));
        rateButton.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        rateButton.setForeground(new java.awt.Color(254, 254, 254));
        rateButton.setText("Rate");
        rateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ratingPanelLayout = new javax.swing.GroupLayout(ratingPanel);
        ratingPanel.setLayout(ratingPanelLayout);
        ratingPanelLayout.setHorizontalGroup(
            ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ratingPanelLayout.createSequentialGroup()
                        .addComponent(labelMovie1)
                        .addGap(18, 18, 18)
                        .addComponent(labelMovieTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ratingPanelLayout.createSequentialGroup()
                        .addComponent(ratingLabel)
                        .addGap(33, 33, 33)
                        .addGroup(ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(starsCB1, 0, 89, Short.MAX_VALUE))
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ratingPanelLayout.setVerticalGroup(
            ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelMovieTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMovie1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ratingLabel)
                    .addComponent(starsCB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rateButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ratingDialog.getContentPane().add(ratingPanel, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lowerToolbar.setMaximumSize(new java.awt.Dimension(32767, 70));
        lowerToolbar.setPreferredSize(new java.awt.Dimension(1439, 70));

        stateToolbar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout lowerToolbarLayout = new javax.swing.GroupLayout(lowerToolbar);
        lowerToolbar.setLayout(lowerToolbarLayout);
        lowerToolbarLayout.setHorizontalGroup(
            lowerToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerToolbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stateToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .addGroup(lowerToolbarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        lowerToolbarLayout.setVerticalGroup(
            lowerToolbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerToolbarLayout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stateToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(lowerToolbar, java.awt.BorderLayout.PAGE_END);

        outputPanel.setLayout(new java.awt.BorderLayout());

        title.setBackground(new java.awt.Color(254, 254, 254));
        title.setFont(new java.awt.Font("Abyssinica SIL", 0, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("RECOMMENDATION SYSTEM");
        outputPanel.add(title, java.awt.BorderLayout.PAGE_START);

        scrollMovieList.setPreferredSize(new java.awt.Dimension(600, 130));

        ratingMoviesList.setAlignmentX(50.0F);
        ratingMoviesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratingMoviesListMouseClicked(evt);
            }
        });
        scrollMovieList.setViewportView(ratingMoviesList);

        outputPanel.add(scrollMovieList, java.awt.BorderLayout.LINE_START);

        recommendedMoviesList.setAlignmentX(50.0F);
        scrollRecommendations.setViewportView(recommendedMoviesList);

        outputPanel.add(scrollRecommendations, java.awt.BorderLayout.CENTER);

        getContentPane().add(outputPanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        setRatingsFile.setText("Ratings");
        setRatingsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRatingsFileActionPerformed(evt);
            }
        });
        fileMenu.add(setRatingsFile);

        setMoviesFile.setText("Movies");
        setMoviesFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setMoviesFileActionPerformed(evt);
            }
        });
        fileMenu.add(setMoviesFile);

        menu.add(fileMenu);

        moviesMenu.setText("Movies");

        suggestMovies.setText("Suggest movies");
        suggestMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suggestMoviesActionPerformed(evt);
            }
        });
        moviesMenu.add(suggestMovies);

        menu.add(moviesMenu);

        helpMenu.setText("Help");

        aboutOption.setText("About");
        aboutOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutOptionActionPerformed(evt);
            }
        });
        helpMenu.add(aboutOption);

        menu.add(helpMenu);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setRatingsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRatingsFileActionPerformed
        // TODO add your handling code here:
        ratingsPath = chooseFile("R");
        stateToolbar.setText("  Ratings data file has been set.");
        if (!"".equals(ratingsPath) && !"".equals(moviesPath)) {
            try {
                readData();
                stateToolbar.setText("  Ratings and movies data has been read.");
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_setRatingsFileActionPerformed

    private void setMoviesFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setMoviesFileActionPerformed
        // TODO add your handling code here:
        moviesPath = chooseFile("M");
        stateToolbar.setText("  Movies data file has been set.");
        if (!"".equals(ratingsPath) && !"".equals(moviesPath)) {
            try {
                readData();
                stateToolbar.setText("  Ratings and movies data has been read.");
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_setMoviesFileActionPerformed

    private void aboutOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutOptionActionPerformed
        // TODO add your handling code here:
        this.aboutDialog.setSize(800, 400);
        this.aboutDialog.setTitle("Help");
        // Set the dialog window in the middle of the screen
        this.aboutDialog.setLocationRelativeTo(null);
        this.aboutDialog.setVisible(true);
    }//GEN-LAST:event_aboutOptionActionPerformed

    private void ratingMoviesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingMoviesListMouseClicked
        // TODO add your handling code here:
        selectedMovie = ratingMoviesList.getSelectedIndex();
        this.labelMovieTitle1.setText(userRatings.getRatings().get(selectedMovie).getTitle());
        // Set the title and its rating
        this.ratingDialog.setTitle("Movie rating");
        this.starsCB1.setSelectedIndex(userRatings.getRatings().get(selectedMovie).getRating()-1);
        // Set the size of the dialog window
        this.ratingDialog.setSize(400, 200);
        // Set the dialog window in the middle of the screen
        this.ratingDialog.setLocationRelativeTo(null);
        this.ratingDialog.setVisible(true);
    }//GEN-LAST:event_ratingMoviesListMouseClicked

    private void rateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateButtonActionPerformed
        // TODO add your handling code here:
        if (selectedMovie != -1) {
            userRatings.getRatings().get(selectedMovie).
                setRating(this.starsCB1.getSelectedIndex()+1);
        }
        updateMoviesList();
        this.ratingDialog.setVisible(false);
    }//GEN-LAST:event_rateButtonActionPerformed

    private void suggestMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suggestMoviesActionPerformed
        // TODO add your handling code here:
        if (!userRatings.getRatings().isEmpty() && !reader.getRatings().isEmpty()
            && !reader.getMovies().isEmpty()) {
            recommender = new Recommender();
            similarity = new TreeMap((Collections.reverseOrder()));
            // Similarity between the active user and the others
            for (UserRatings user: reader.getRatings()) {
                similarity.put(recommender.getPearsonSimilarity(userRatings, user), user.getUserId());
            }
            // Gets K user to form the neighborhood
            TreeMap<Integer, Double> neighborhood = new TreeMap();
            for (Double sim: similarity.keySet()) {
                if (neighborhood.size() < K)
                    neighborhood.put(similarity.get(sim), sim);
                else
                    break;
            }
            // Gets the suggested movies
            TreeMap<Double, Integer> suggestedMovies =
                recommender.getRecommendations(userRatings, reader.getMovies(),
                    reader.getRatings(), neighborhood);

            // Shows the suggested movies
            DefaultListModel suggMoviesModel = new DefaultListModel();
            for (Double rating: suggestedMovies.keySet()) {
                System.out.println(rating + " vs. " + rating.intValue());
                suggMoviesModel.addElement("  " +rating.intValue() + "* " +
                    reader.getMovies().get(suggestedMovies.get(rating)));
            }
            this.recommendedMoviesList.setModel(suggMoviesModel);
        }
    }//GEN-LAST:event_suggestMoviesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog aboutDialog;
    private javax.swing.JMenuItem aboutOption;
    private javax.swing.JPanel aboutScreen;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel help11;
    private javax.swing.JLabel help12;
    private javax.swing.JLabel help13;
    private javax.swing.JLabel help14;
    private javax.swing.JLabel help15;
    private javax.swing.JLabel help16;
    private javax.swing.JLabel help17;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel helpOption1;
    private javax.swing.JLabel helpOption2;
    private javax.swing.JLabel helpTitle;
    private javax.swing.JLabel labelMovie1;
    private javax.swing.JLabel labelMovieTitle1;
    private javax.swing.JPanel lowerToolbar;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu moviesMenu;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JButton rateButton;
    private javax.swing.JDialog ratingDialog;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JList<String> ratingMoviesList;
    private javax.swing.JPanel ratingPanel;
    private javax.swing.JList<String> recommendedMoviesList;
    private javax.swing.JScrollPane scrollMovieList;
    private javax.swing.JScrollPane scrollRecommendations;
    private javax.swing.JMenuItem setMoviesFile;
    private javax.swing.JMenuItem setRatingsFile;
    private javax.swing.JComboBox<String> starsCB1;
    private javax.swing.JLabel stateToolbar;
    private javax.swing.JMenuItem suggestMovies;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
