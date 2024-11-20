package Sudoku;

import java.util.*;

import static java.lang.System.exit;

public class Backtracking {

  public static String getVariable(ST<String, String> config) {

    // Retrieve a variable based on a heuristic or the next 'unfilled' one if there is no heuristic
    for (String s : config) {
      if (config.get(s).equalsIgnoreCase(""))
        return s;
    }

    // Get variable failed (all variables have been coloured)
    return null;
  }

  public static SET<String> orderDomainValue(String variable, ST<String, SET<String>> domain) {

    // Return the SET of domain values for the variable
    return domain.get(variable);
  }

  public static boolean complete(ST<String, String> config) {

    for (String s : config) {
      //if we find a variable in the config with no value, then this means that the config is NOT complete
      if (config.get(s).equalsIgnoreCase(""))
        return false;
    }

    //ALL variables in config have a value, so the configuration is complete
    return true;
  }


  public static boolean consistent(String value, String variable, ST<String, String> config, Graph g) {

    //we need to get the adjacency list for the variable
    for (String adj : g.adjacentTo(variable)) {
      //if the adjacency list member's value is equal to the variable's selected value, then consistency fails
      if (config.get(adj) != null && config.get(adj).equalsIgnoreCase(value)) {
        //consistency check fail
        return false;
      }
    }

    //consistency check passed according to the variable's adjacancy list
    return true;
  }

  public static boolean consistent(String value, String variable, ST<String, String> config,
                                   ST<String, ST<String, ST<String, SET<String>>>> constraintsTable) {
    //we need to get the constraint list for the variable
    for (String constraints : constraintsTable.get(variable)) {
      //if the adjacency list member's value is equal to the variable's selected value, then consistency fails
      if (!config.get(constraints).equals("") && !(constraintsTable.get(constraints).get(value).contains(config.get(constraints)))) {
        return false;
      }
    }

    //consistency check passed according to the variable's adjacancy list
    return true;
  }

  public static ST<String, String> backtracking(ST<String, String> config, ST<String, SET<String>> domain, Graph g) {

    //recursion base case - check configuration completeness
    if (complete(config))
      return config;

    ST<String, String> result = null;

    //get a variable
    String v = getVariable(config);

    //get a SET of all the variable's values
    SET<String> vu = orderDomainValue(v, domain);

    //loop through all the variable's values
    for (String u : vu) {
      //if(consistent(u, v, config, g)) {

      if (consistent(u, v, config, g)) {
        config.put(v, u);

        result = backtracking(config, domain, g);
        if (result != null)
          return result;
        config.put(v, "");
      }
    }
    return null;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    Graph G = new Graph();
    String grille[][] = {{"", "", "", "", "", "", "7", "2", ""},
      {"", "1", "3", "", "", "7", "", "4", ""},
      {"2", "", "", "", "5", "", "", "", "8"},
      {"", "", "", "", "", "6", "4", "7", ""},
      {"7", "8", "", "4", "", "1", "", "3", "6"},
      {"", "9", "4", "3", "", "", "", "", ""},
      {"3", "", "", "", "6", "", "", "", "4"},
      {"", "7", "", "5", "", "", "8", "1", ""},
      {"", "5", "8", "", "", "", "", "", ""}};

    // Affichage de la grille initiale
    for (int i = 0; i < 9; i++) {  // Ligne
      System.out.println();
      for (int j = 0; j < 9; j++)   // Colonne
        if (((String) grille[i][j]).length() != 0)
          System.out.print(grille[i][j]);
        else
          System.out.print(".");
    }


    // Contraintes au niveau des lignes
    for (int i = 1; i <= 9; i++)    {   // Ligne
      for (int j = 1; j <= 8; j++) {  // Colonne
        for (int k = j + 1; k <= 9; k++) {
          String var1 = "x" + i + j;
          String var2 = "x" + i + k;

          G.addEdge(var1, var2);
        }
      }
    }

    // Contaraintes au niveau des colonnes
    for (int i = 1; i <= 9; i++) {   // Colonne
      for (int j = 1; j <= 8; j++) {   // Ligne
        for (int k = j + 1; k <= 9; k++) {
          String var1 = "x" + j + i;
          String var2 = "x" + k + i;

          G.addEdge(var1, var2);
        }
      }
    }

    // Contraintes au niveau des sous-grilles
    for (int ii = 1; ii <= 3; ii++)       // Ligne de la sous-grille
      for (int jj = 1; jj <= 3; jj++) {    // Colonne de la sous-grille
        int toplefti = (ii - 1) * 3;
        int topleftj = (jj - 1) * 3;

        for (int i = 1; i <= 3; i++)       // Colonne dans la sous-grille
          for (int j = 1; j <= 3; j++)      // Ligne dans de la sous-grille
          {
            for (int k = 1; k <= 3; k++)       // Lignes et colonnes impliquées dans des contraintes dans la sous-grille
              for (int l = 1; l <= 3; l++)
                if (k != i && l != j) {
                  String var1 = "x" + (toplefti + i) + (topleftj + j);
                  String var2 = "x" + (toplefti + k) + (topleftj + l);

                  G.addEdge(var1, var2);
                }
          }
      }

    // Tables des domaines
    ST<String, SET<String>> domainTable = new ST<String, SET<String>>();

    // Remplir les Domaines
    Object[][] domains = new Object[9][9];

    for (int i = 0; i < 9; i++)       // Colonne
      for (int j = 0; j < 9; j++)      // Ligne
        domains[i][j] = new SET<String>();

    for (int i = 0; i < 9; i++)       // Colonne
      for (int j = 0; j < 9; j++) {     // Ligne

        if (((String) grille[i][j]).length() != 0)
          ((SET<String>) domains[i][j]).add(new String((String) grille[i][j])); // Domaine avec une seule valeur (case remplie )
        else {

          for (int k = 1; k <= 9; k++)
            ((SET<String>) domains[i][j]).add("" + k);// Domaine avec {1, 2, 3, ..., 9} ( case vide)
        }
      }

    // Ajouter les domaines à la table
    for (int i = 1; i <= 9; i++)
      for (int j = 1; j <= 9; j++)
        domainTable.put("x" + i + j, ((SET<String>) domains[i - 1][j - 1]));

    // Configuration initiale
    ST<String, String> config = new ST<String, String>();

    for (int i = 1; i <= 9; i++)       // Ligne
      for (int j = 1; j <= 9; j++)   // Colonne
        config.put("x" + i + "" + j, ""); // Variables non affectées


    System.out.println();

    long temps1= new Date().getTime() ;
    ST<String, SET<String>> domainTable_after_ac=null;
/*
    System.out.println("----------------------------------------------------------------------");
    int choix = 0 ;
    System.out.print("choisir \n| 1:AC-1; \n| 3:AC-3; \n| 4:AC-4 ; \n===>");
    Scanner sc = new Scanner(System.in);
    choix = sc.nextInt();
    System.out.println("----------------------------------------------------------------------");

    switch (choix){
      case 1:
        temps1 = new Date().getTime();
        System.out.println();

        domainTable_after_ac = algo_AC_1(domainTable , G);

        System.out.println("time of AC1 : "+(new Date().getTime() - temps1));
        break;
      case 3:
        temps1 = new Date().getTime();
        System.out.println();

        domainTable_after_ac = algo_AC_3(domainTable , G);

        System.out.println("time of AC3 : "+(new Date().getTime() - temps1));
        break;
      case 4:
        temps1 = new Date().getTime();
        System.out.println();

        domainTable_after_ac = algo_AC_4(domainTable , G);

        System.out.println("time of AC4 : "+(new Date().getTime() - temps1));
        break;
      default:
        exit(0);

    }
    sc.close();

*/



    /*
    this is where to add the function to check the "constraints" AC-3 , AC-4 and AC-1
            -> algo_AC_1(domainTable , G);
            -> algo_AC_3(domainTable , G);
            -> algo_AC_4(domainTable , G);

            utiliser :
            domainTable_after_ac =  algo_AC_1(domainTable , G);
     */


    System.out.println("\nCalcul en cours ... ");

    ST<String, String> result = backtracking(config,  domainTable, G);


    // Afficher la solution
    for (int i = 1; i <= 9; i++) {  // Ligne
      System.out.println("");
      for (int j = 1; j <= 9; j++)   // Colonne
        System.out.print(config.get("x" + i + j) + " ");
    }
    System.out.println();
    System.out.println("time of total : "+(new Date().getTime() - temps1));


  }

  private static ST<String, SET<String>> algo_AC_1(ST<String, SET<String>> domainTable, Graph graph) {
    boolean changer = true;

    List<String[]> constraint = new ArrayList<>();
    for (String variable : domainTable ){
      for (String neighbor : graph.adjacentTo(variable)) {
        constraint.add(new String[]{variable , neighbor});
      }
    }

    while (changer) {
      changer = false;

      for (String[] arc : constraint){
        String i = arc[0];
        String j = arc[1];

        var domain_i = domainTable.get(i);
        var domain_j = domainTable.get(j);

        var temp_domain_i = new SET<String>();

        for (var x : domain_i){
          boolean satisfaitLeContrainte = false;
          for (var y : domain_j){
            if (verifierConstraint(x,y)){
              satisfaitLeContrainte = true;
              break;
            }
          }
          if (satisfaitLeContrainte){
            temp_domain_i.add(x);
          }
        }


        if (temp_domain_i.size() < domain_i.size()){
          domainTable.put(i, temp_domain_i);
          changer = true ;

        }

      }


    }
    return domainTable;

  }

  private static ST<String, SET<String>> algo_AC_3(ST<String, SET<String>> domainTable, Graph graph) {
    Queue<String[]> queue = new LinkedList<>();

    // Initialize queue with all arcs from the graph
    for (String variable : domainTable) {
      for (String neighbor : graph.adjacentTo(variable)) {
        queue.add(new String[]{variable, neighbor});
      }
    }

    // Process each arc in the queue
    while (!queue.isEmpty()) {
      String[] arc = queue.poll();
      String Xi = arc[0];
      String Xj = arc[1];

      // If the domain of Xi was revised, check for consistency
      if (revise(domainTable, Xi, Xj)) {
        // If domain of Xi is empty, there is no solution
        if (domainTable.get(Xi).size() == 0) {
          return null; // No solution exists
        }

        // Add all neighbors of Xi (excluding Xj) to the queue
        for (String Xk : graph.adjacentTo(Xi)) {
          if (!Xk.equals(Xj)) {
            queue.add(new String[]{Xk, Xi});
          }
        }
      }

    }
    return domainTable; // Return the revised domain table after applying AC-3
  }



  // Helper function to revise the domain of Xi based on its constraint with Xj
  private static boolean revise(ST<String, SET<String>> domainTable, String Xi, String Xj) {
    boolean revised = false;
    SET<String> domainXi = domainTable.get(Xi);
    SET<String> domainXj = domainTable.get(Xj);

    // Collect items to remove to avoid ConcurrentModificationException
    List<String> toRemove = new ArrayList<>();

    for (String x : domainXi) {
      // Check if there exists no value y in domain of Xj that satisfies the constraint
      boolean satisfiesConstraint = false;
      for (String y : domainXj) {
        if (verifierConstraint(x,y)) { // Here we assume a "not equal" constraint, typical for Sudoku
          satisfiesConstraint = true;
          break;
        }
      }
      // If no such y exists, mark x for removal
      if (!satisfiesConstraint) {
        toRemove.add(x);
        revised = true;
      }
    }

    // Remove items outside the loop to avoid concurrent modification
    for (String value : toRemove){
      domainXi.remove(value);

    }


    return revised;
  }

  private static boolean verifierConstraint(String x, String y){
    return !x.equals(y);
  }


  private static ST<String, SET<String>> algo_AC_4(ST<String, SET<String>> domainTable, Graph graph) {
    // Initialize support counter and inverse support sets
    Map<String, Map<String, Integer>> supportCount = new HashMap<>();
    Map<String, Map<String, SET<String>>> supportedValues = new HashMap<>();
    Queue<String[]> queue = new LinkedList<>();

    // Initialize support structures and queue
    for (String variable : domainTable) {
      for (String value : domainTable.get(variable)) {
        // Initialize the support count for each value of the variable
        supportCount.putIfAbsent(variable, new HashMap<>());
        supportCount.get(variable).put(value, 0);

        // Initialize supported values for each variable and value
        supportedValues.putIfAbsent(variable, new HashMap<>());
        supportedValues.get(variable).putIfAbsent(value, new SET<>()); // Ensure the SET is initialized

        // Check adjacent variables for support
        for (String neighbor : graph.adjacentTo(variable)) {
          boolean hasSupport = false;
          for (String neighborValue : domainTable.get(neighbor)) {
            if (verifierConstraint(value, neighborValue)) {
              hasSupport = true;
              supportCount.get(variable).put(value, supportCount.get(variable).get(value) + 1);

              // Initialize nested map and SET if not already present
              supportedValues.putIfAbsent(neighbor, new HashMap<>());
              supportedValues.get(neighbor).putIfAbsent(neighborValue, new SET<>());

              supportedValues.get(neighbor).get(neighborValue).add(value);
            }
          }
          if (!hasSupport) {
            queue.add(new String[]{variable, value});
          }
        }
      }
    }

    // Process the queue to remove unsupported values
    while (!queue.isEmpty()) {
      String[] pair = queue.poll();
      String variable = pair[0];
      String value = pair[1];

      // Remove the unsupported value from the domain
      domainTable.get(variable).remove(value);
      if (domainTable.get(variable).size()==0) {
        return null; // No solution if a domain becomes empty
      }

      // Propagate changes to neighbors
      for (String neighbor : graph.adjacentTo(variable)) {
        for (String neighborValue : domainTable.get(neighbor)) {
          if (supportedValues.get(variable).get(value).contains(neighborValue)) {
            supportedValues.get(variable).get(value).remove(neighborValue);
            supportCount.get(neighbor).put(neighborValue, supportCount.get(neighbor).get(neighborValue) - 1);
            if (supportCount.get(neighbor).get(neighborValue) == 0) {
              queue.add(new String[]{neighbor, neighborValue});
            }
          }
        }
      }
    }

    return domainTable;
  }






}
