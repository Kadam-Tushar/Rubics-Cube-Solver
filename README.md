# Rubics-Cube-Solver
A program which solves any state 2 by 2 rubics cube in less than 14 moves (Optimally)
![Cube ](https://images-na.ssl-images-amazon.com/images/I/61fkE%2BolXoL._SY355_.jpg)

## Algorithm Used - Breadth First Search

Inspired from **MIT's Analysis Of Algorithms** Lecture of **Breadth First Search**.

I used  idea of [Senary Numbers](https://en.wikipedia.org/wiki/Senary) for generating all the combinations of moves.I got this idea while reading [KitN](https://github.com/KitN)'s  Code. 

It has been mathematically proved that any state of 2 by 2 cube can be solved in **less than or equal to 14 moves**.

There are mainly  **6** types of moves:**(L,R,U,D,F,B)**

See the image to know about these moves mean :

![image of cube](https://github.com/Kadam-Tushar/Rubics-Cube-Solver/blob/master/img2.JPG)
Credits :https://www.youcandothecube.com


A valid combination of these 6 moves will definitely result into solved state of cube and we know that maximum 14 such moves are required.

If we map these 6 different moves with numbers and generate all the combinations of these numbers with max length 14 and if we try all of them,then we would get optimal solution.

For eg
if we map numbers with moves like this-

**0-L**

**1-R**

**2-U**

**3-D**

**4-F**

**5-B**


then **01** will correspond to **LR** move while **5411** will correspond to **BFRR** move.

So basically I am mapping  **base-6** numbers to **moves**.

We can generate all these numbers from  0 to 55555555555555 (base 6,length 14) and check these moves are going to give us solved state or not. 

In worst case it will take **6^14** iterations but average time required to solve is quite less.

I used **breadth first search** to find minimum possible moves to reach at final solved state.



### How to Run the proogram ?
Just compile **rubics2.java** and give your input state of cube.

### How to give Input ?
Input will be taken for each face of cube. Starting from **Front Face(F),Upper(U),Left(L),Right(R),Down(d) and Back(B)**.

Following image represents how colour of each  cell will be stored in program.

Numbers on each cell will represent when program will take input of that cell.


![image of cube](https://github.com/Kadam-Tushar/Rubics-Cube-Solver/blob/master/img1.JPG)


Colours used in this program :

**Red-r Green-g Blue-b White-w Yellow-y Orange-o**

for eg input can be .

**oogg**

**boyy**

**ygyr**

**bwow**

**wwgr**

**brbr**



Program will take input cube by taking first Front Face input with  following colours.

**Orange Orange**

**Green  Green**





