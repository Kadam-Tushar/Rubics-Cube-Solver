import java.util.*;
public class rubics2 {

 //For mapping numbers with faces of moves and vice cersa 
 static HashMap < String, Integer > hm = new HashMap < > ();
 static HashMap < Integer, String > revhm = new HashMap < > ();


 public static void main(String[] args) {

  init(); //initialisation of mappings 
  Scanner sc = new Scanner(System.in);
  System.out.println("UnFold the Cube in described manner in Read.me and give colour of each cell ");
  char[][][] chk = new char[6][2][2];

  // ------------->Input<------------------//


  for (int i = 0; i < 6; ++i) {
   int j = 0;
   int ind = 0;
   String str = sc.next();
   for (; j < 2; ++j) {

    for (int k = 0; k < 2; ++k) {
     chk[i][j][k] = str.charAt(ind++);
    }

   }

  }

  // ------------->Input Over <------------------//





  //Checking one by one every move through BFS  


  //------>Start Solving<-------//

  state initial = new state(chk);
  String last = "55555555555555";
  long decimalOfLast = Long.parseLong(last, 6);
  String movesList = "";
  for (long i = 0; i < decimalOfLast; ++i) {
   movesList = Long.toString(i, 6);
   state after = apply(movesList, initial);
   if (isComplete(after)) {
    System.out.println("Voila ");
    break;
   }

  }

  //------>Done Solving<-------//


  //Printing optimal Moves
  StringBuilder ans = new StringBuilder();
  for (int i = 0; i < movesList.length(); ++i) {
   ans.append(revhm.get(Integer.parseInt(movesList.charAt(i) + "")) + " ");
  }
  System.out.println(ans);

 }




 static void init() {
  hm.put("f", 0);
  hm.put("u", 1);
  hm.put("l", 2);
  hm.put("r", 3);
  hm.put("d", 4);
  hm.put("b", 5);

  revhm.put(0, "f");
  revhm.put(1, "u");
  revhm.put(2, "l");
  revhm.put(3, "r");
  revhm.put(4, "d");
  revhm.put(5, "b");

 }

 static state apply(String move, state curr) {

  state nxt = copy(curr);
  for (int i = 0; i < move.length(); ++i) {
   int m = Integer.parseInt(move.charAt(i) + "");

   nxt = giveState(m, nxt);


  }
  return nxt;
 }

 static boolean isComplete(state curr) {

  char colour[][][] = curr.colour;
  boolean f = true;
  outer: for (int i = 0; i < 6; ++i) {
   char c = colour[i][0][0];
   for (int j = 0; j < 2; ++j) {
    for (int k = 0; k < 2; ++k) {
     if (colour[i][j][k] != c) {
      f = false;

      break outer;
     }
    }
   }
  }
  return f;
 }



 static state copy(state curr) {
  char[][][] ans = new char[6][2][2];
  for (int i = 0; i < 6; ++i) {
   for (int j = 0; j < 2; ++j) {
    for (int k = 0; k < 2; ++k) {
     ans[i][j][k] = curr.colour[i][j][k];
    }
   }
  }


  return new state(ans);
 }


 static state giveState(int x, state curr) {
  String mapp = revhm.get(x);
  state nxt;
  switch (mapp) {
   case "f":
    {
     nxt = F(curr);

     break;
    }

   case "u":
    {
     nxt = U(curr);
     break;
    }

   case "l":
    {
     nxt = L(curr);
     break;

    }

   case "r":
    {
     nxt = R(curr);
     break;
    }

   case "d":
    {
     nxt = D(curr);
     break;
    }

   default:
    {
     nxt = B(curr);
     break;
    }




  }
  return nxt;
 }
 static void faceRotateClk(String face, char[][][] colour) {
  char buffer = colour[hm.get(face)][0][0];
  colour[hm.get(face)][0][0] = colour[hm.get(face)][1][0];
  colour[hm.get(face)][1][0] = colour[hm.get(face)][1][1];
  colour[hm.get(face)][1][1] = colour[hm.get(face)][0][1];
  colour[hm.get(face)][0][1] = buffer;
 }

 static void faceRotateAntiClk(String face, char[][][] colour) {
  char buffer = colour[hm.get(face)][0][0];
  colour[hm.get(face)][0][0] = colour[hm.get(face)][0][1];
  colour[hm.get(face)][0][1] = colour[hm.get(face)][1][1];
  colour[hm.get(face)][1][1] = colour[hm.get(face)][1][0];
  colour[hm.get(face)][1][0] = buffer;
 }



 static state R(state curr) {
  state AnsVar = copy(curr);

  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];

  buffer[0] = ans[hm.get("u")][0][1];
  buffer[1] = ans[hm.get("u")][1][1];


  ans[hm.get("u")][0][1] = ans[hm.get("f")][0][1];
  ans[hm.get("u")][1][1] = ans[hm.get("f")][1][1];

  ans[hm.get("f")][0][1] = ans[hm.get("d")][0][1];
  ans[hm.get("f")][1][1] = ans[hm.get("d")][1][1];

  ans[hm.get("d")][0][1] = ans[hm.get("b")][1][0];
  ans[hm.get("d")][1][1] = ans[hm.get("b")][0][0];

  ans[hm.get("b")][1][0] = buffer[0];
  ans[hm.get("b")][0][0] = buffer[1];
  faceRotateClk("r", ans);

  return new state(ans);

 }
 static state Ri(state curr) {
  return R(R(R(curr)));
 }
 static state L(state curr) {

  state AnsVar = copy(curr);
  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];

  buffer[0] = ans[hm.get("u")][0][0];
  buffer[1] = ans[hm.get("u")][1][0];


  ans[hm.get("u")][0][0] = ans[hm.get("f")][0][0];
  ans[hm.get("u")][1][0] = ans[hm.get("f")][1][0];

  ans[hm.get("f")][0][0] = ans[hm.get("d")][0][0];
  ans[hm.get("f")][1][0] = ans[hm.get("d")][1][0];

  ans[hm.get("d")][0][0] = ans[hm.get("b")][1][1];
  ans[hm.get("d")][1][0] = ans[hm.get("b")][0][1];

  ans[hm.get("b")][1][1] = buffer[0];
  ans[hm.get("b")][0][1] = buffer[1];
  faceRotateAntiClk("l", ans);

  return new state(ans);
 }
 static state Li(state curr) {
  return L(L(L(curr)));
 }



 static state D(state curr) {
  state AnsVar = copy(curr);
  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];



  buffer[0] = ans[hm.get("l")][1][0];
  buffer[1] = ans[hm.get("l")][1][1];

  ans[hm.get("l")][1][0] = ans[hm.get("f")][1][0];
  ans[hm.get("l")][1][1] = ans[hm.get("f")][1][1];

  ans[hm.get("f")][1][0] = ans[hm.get("r")][1][0];
  ans[hm.get("f")][1][1] = ans[hm.get("r")][1][1];

  ans[hm.get("r")][1][0] = ans[hm.get("b")][1][0];
  ans[hm.get("r")][1][1] = ans[hm.get("b")][1][1];

  ans[hm.get("b")][1][0] = buffer[0];
  ans[hm.get("b")][1][1] = buffer[1];


  faceRotateAntiClk("d", ans);

  return new state(ans);

 }
 static state Di(state curr) {
  return D(D(D(curr)));
 }

 static state U(state curr) {
  state AnsVar = copy(curr);
  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];



  buffer[0] = ans[hm.get("l")][0][0];
  buffer[1] = ans[hm.get("l")][0][1];

  ans[hm.get("l")][0][0] = ans[hm.get("f")][0][0];
  ans[hm.get("l")][0][1] = ans[hm.get("f")][0][1];

  ans[hm.get("f")][0][0] = ans[hm.get("r")][0][0];
  ans[hm.get("f")][0][1] = ans[hm.get("r")][0][1];

  ans[hm.get("r")][0][0] = ans[hm.get("b")][0][0];
  ans[hm.get("r")][0][1] = ans[hm.get("b")][0][1];

  ans[hm.get("b")][0][0] = buffer[0];
  ans[hm.get("b")][0][1] = buffer[1];


  faceRotateClk("u", ans);

  return new state(ans);

 }
 static state Ui(state curr) {
  return U(U(U(curr)));
 }




 static state F(state curr) {
  state AnsVar = copy(curr);
  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];

  buffer[0] = ans[hm.get("u")][1][0];
  buffer[1] = ans[hm.get("u")][1][1];

  ans[hm.get("u")][1][0] = ans[hm.get("l")][1][1];
  ans[hm.get("u")][1][1] = ans[hm.get("l")][0][1];

  ans[hm.get("l")][0][1] = ans[hm.get("d")][0][0];
  ans[hm.get("l")][1][1] = ans[hm.get("d")][0][1];

  ans[hm.get("d")][0][0] = ans[hm.get("r")][1][0];
  ans[hm.get("d")][0][1] = ans[hm.get("r")][0][0];

  ans[hm.get("r")][0][0] = buffer[0];
  ans[hm.get("r")][1][0] = buffer[1];

  faceRotateClk("f", ans);

  return new state(ans);

 }
 static state Fi(state curr) {
  return F(F(F(curr)));
 }



 static state B(state curr) {
  state AnsVar = copy(curr);
  char[][][] ans = AnsVar.colour;
  char[] buffer = new char[2];

  buffer[0] = ans[hm.get("u")][0][0];
  buffer[1] = ans[hm.get("u")][0][1];

  ans[hm.get("u")][0][0] = ans[hm.get("l")][1][0];
  ans[hm.get("u")][0][1] = ans[hm.get("l")][0][0];

  ans[hm.get("l")][1][0] = ans[hm.get("d")][1][1];
  ans[hm.get("l")][0][0] = ans[hm.get("d")][1][0];

  ans[hm.get("d")][1][1] = ans[hm.get("r")][0][1];
  ans[hm.get("d")][1][0] = ans[hm.get("r")][1][1];

  ans[hm.get("r")][0][1] = buffer[0];
  ans[hm.get("r")][1][1] = buffer[1];


  faceRotateAntiClk("b", ans);

  return new state(ans);

 }
 static state Bi(state curr) {
  return B(B(B(curr)));
 }


}
class state {
 char[][][] colour;
 state(char[][][] colour) {
  this.colour = colour;
 }


}
