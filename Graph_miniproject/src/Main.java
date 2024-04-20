import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	
	
	
    
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameField = new JTextField(20);
    
    private JLabel skillsLabel = new JLabel("Skills:");
    private JTextField skillsField = new JTextField(20);
    
    private JLabel interestsLabel = new JLabel("Interests:");
    private JTextField interestsField = new JTextField(20);
    
    private JLabel careerGoalLabel = new JLabel("Career Goal:");
    private JTextField careerGoalField = new JTextField(20);
    
    private JButton submitButton = new JButton("Submit");
    
    private JButton hideButton = new JButton("Close Form");
    
    private JLabel formStatusLabel = new JLabel("Form Status: Not yet submitted");

    public Main() {
    	
    	
        setTitle("User Registration: ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(skillsLabel, gbc);

        gbc.gridx = 1;
        panel.add(skillsField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(interestsLabel, gbc);
        
        gbc.gridx = 1;
        panel.add(interestsField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(careerGoalLabel, gbc);
        
        gbc.gridx = 1;
        panel.add(careerGoalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(hideButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(formStatusLabel, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                	setVisible(false);
          
            }
        });
        
        
        // attach an ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // create a File object for the output file (change "output.txt" to a filename of your choice)
                    File outputFile = new File("JobSeekers.txt");
                    
                    // create a FileWriter object to write to the output file
                    FileWriter writer = new FileWriter(outputFile, true);
                    
                    // write the form information to the output file
                    String name = nameField.getText();
                    String skills = skillsField.getText();
                    String hobbies = interestsField.getText();
                    String jobTitle = careerGoalField.getText();
                    
                    // Create a comma-separated line of data
                    String data = name + "," + String.join(" ", skills) + "," + String.join(" ", hobbies) + "," + jobTitle;
                    
                    writer.write(data + "\n"); // Write data line to file and append new line character
                    
                    
                    // close the writer to flush the buffer and release resources
                    writer.close();
                    
                    // show a dialog box indicating that writing was successful
                    JOptionPane.showMessageDialog(Main.this, "Form submitted successfully!");
                    
                    // update the form status label
                    formStatusLabel.setText("Form Status: Submitted");
                } catch(IOException ex) {
                    // if there is an error while writing to the file, show an error message
                    JOptionPane.showMessageDialog(Main.this, "Error writing to file: " + ex.getMessage());
                }
            }
        });
        
        
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new Main();
        });
        
        List<JobSeeker> jobSeekers = new ArrayList<>();
        List<Business> businesses = new ArrayList<>();
        
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("JobSeekers.txt"))) {
          String line;
          while ((line = br.readLine()) != null) {
        	  String[] tokens = line.split(",");
        	  
        	  if (tokens.length >= 4) {
        	    String name = tokens[0].trim();
        	    List<String> skills = Arrays.asList(tokens[1].trim().split(" "));
        	    List<String> hobbies = Arrays.asList(tokens[2].trim().split(" "));
        	    String jobTitle = tokens[3].trim();
        	    
        	    JobSeeker newJobSeeker = new JobSeeker(name, skills, hobbies, jobTitle);
        	    jobSeekers.add(newJobSeeker);
        	  }
        	}
          
          
        } catch (IOException e) {
          System.out.println("Error reading file.");
        }
        
        try (BufferedReader br1 = new BufferedReader(new FileReader("Businesses.txt"))) {
            String line;
            while ((line = br1.readLine()) != null) {
          	  String[] tokens = line.split(",");
          	  
          	  if (tokens.length >= 4) {
          	    String name = tokens[0].trim();
          	    List<String> skills = Arrays.asList(tokens[1].trim().split(" "));
          	    List<String> hobbies = Arrays.asList(tokens[2].trim().split(" "));
          	    String jobTitle = tokens[3].trim();
          	    
          	    Business newBusiness = new Business(name, skills, hobbies, jobTitle);
          	    businesses.add(newBusiness);
          	  }
          	}
            
            // Printing all the Job Seekers
            //for (Business js : businesses) {
            //  System.out.println(js.getName());
          //  }
            
          } catch (IOException e) {
            System.out.println("Error reading file.");
          }
        
        Graph<String, Edge> graph = new SparseGraph<>();
        

        // Add job seekers as vertices
        for (JobSeeker jobSeeker : jobSeekers) {
            graph.addVertex(jobSeeker.getName());
        }

        // Add businesses as vertices
        for (Business business : businesses) {
            graph.addVertex(business.getName());
        }

        // Add edges between job seekers and businesses based on shared skills and interests
        for (JobSeeker jobSeeker : jobSeekers) {
            for (Business business : businesses) {
                int weight = getSharedSkillsAndInterests(jobSeeker, business);
                if (weight > 0) {
                    graph.addEdge(new Edge(weight), jobSeeker.getName(), business.getName(), EdgeType.UNDIRECTED);
                }
            }
        }

        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter job seeker name:");
			String name = scanner.nextLine();
			JobSeeker selectedJobSeeker = null;
			for (JobSeeker jobSeeker : jobSeekers) {
			    if (jobSeeker.getName().equals(name)) {
			        selectedJobSeeker = jobSeeker;
			        break;
			    }
			}
			if (selectedJobSeeker == null) {
			    System.out.println("No such job seeker found!");		   
			}
			
			DijkstraShortestPath<String, Edge> shortestPathAlgorithm = new DijkstraShortestPath<>(graph);
			List<Edge> shortestPaths = shortestPathAlgorithm.getPath(selectedJobSeeker.getName(), name);
			List<Business> matches = new ArrayList<>();
			for (Edge edge : shortestPaths) {
			    String oppositeVertex = graph.getOpposite(selectedJobSeeker.getName(), edge);
			    Object oppositeVertexObject = graph.getVertices().stream().filter(v -> v.equals(oppositeVertex)).findFirst().orElse(null);
			    if (oppositeVertexObject instanceof Business) {
			        matches.add((Business) oppositeVertexObject);
			      
			    }
			}

		}
        
        GraphDrawer.drawGraph(graph);
    }
    
    

    private static int getSharedSkillsAndInterests(JobSeeker jobSeeker, Business business) {
        int skillsWeight = 0;
        for (String skill : jobSeeker.getSkills()) {
            if (business.getRequiredSkills().contains(skill)) {
                skillsWeight++;
            }
        }

        int interestsWeight = 0;
        for (String interest : jobSeeker.getInterests()) {
            if (business.getRequiredInterests().contains(interest)) {
                interestsWeight++;
            }
        }

        return skillsWeight + interestsWeight;
    }
      
 }

