# Task 3

## Started

JAN 18, 2019, 09:45 PM

## Finished

JAN 25, 2019, 11:54 AM (check git logs for actual coding time)

## How to run

On command line:

javac TalentHunt.java                                                     
java TalentHunt < input.txt                                                     

Input can be changed from input text file.

## Explanation of my algorithm

Unlike the previous two tasks (which I feel the function names and variables speak for themselves), I felt the need to explain how my algorithm works.

Most of my algorithmic part resides in the function: public List<Pair> matchJobAndCadidate().[line 72]

My first step is to greedily match all candidates with their first choice jobs (first job that appears in candidate matrix). [line 77 - line 86]

The jobs that have been selected in put into a set which will be used as a count of distinct jobs selected. Therefore, while not all distinct jobs have been selected, the next part repeats in a while loop. [line 88]

Inside the while loop, I will compare two candidates and check if they have chosen the same job. [line 91]

If they have chosen the same job, I will look at these two candidates from job's perspective and take the one that job favors more (whichever candidate that appears earlier in job matrix). [line 92-97]

For the candidate that has not been chosen by the job, I will set his job preference to this next favored job. If that next favoured job has not been added to the set, I will add to the set. [line 98-108]

This allows us to greedily maximize the relevancy of the pairing that assures there will be no job or candidate match which would BOTH be mor relevant to each other for any other pair.

## Explanation of differences in the answer

### Definition of accepted answer

Summarized from the question description:
1. All candidates are matched to a job and vice versa, in a 1-1 relationship.
2. The matching is stable, in the sense that no job or candidate which are not matched to each other, would both be more relevant to each other than their current match.

### Sample Input File 1

Input:

j1: c3 c4 c2 c1
j2: c2 c3 c1 c4
j3: c4 c2 c3 c1
j4: c4 c3 c1 c2

c1: j4 j1 j2 j3
c2: j1 j2 j4 j3
c3: j1 j3 j4 j2
c4: j1 j3 j4 j2

Expected:

c1 j4
c2 j2
c3 j1
c4 j3

Actual:

c1 j4
c2 j2
c3 j1
c4 j3

My program produces the same answer.

### Sample Input File 2

Input:

j1: c2 c3 c4 c1 c5
j2: c4 c3 c1 c5 c2
j3: c4 c3 c5 c1 c2
j4: c2 c3 c4 c1 c5
j5: c4 c1 c2 c5 c3


c1: j2 j5 j1 j3 j4
c2: j3 j5 j4 j2 j1
c3: j1 j4 j5 j2 j3
c4: j5 j1 j4 j3 j2
c5: j4 j3 j1 j5 j2

Expected:

c1 j2
c2 j4
c3 j1
c4 j5
c5 j3

Actual:

c1 j2
c2 j3
c3 j1
c4 j5
c5 j4

I will only explain for the candiate-job pairs which are different in expected and actual. Also, I will explain from the actual (my program's) perspective.

1. Actual: c2-j3 Expected: c2-j4

c2-j3 is stable because j3 is ranked higher than j4 from c2's perspective. Therefore, c2-j4 cannot be BOTH more relevant to each other.

2. Actual: c5-j4 Expected: c5-j3

c5-j4 is stable because j4 is ranked higher than h3 from c5's perspective. Therefore, c5-j3 cannot be BOTH more relevant to each other.

### Sample Input File 3

Input:

j1: c8 c4 c2 c3 c7 c6 c1 c5
j2: c3 c5 c7 c8 c1 c6 c2 c4
j3: c5 c1 c4 c2 c6 c7 c8 c3
j4: c5 c3 c1 c6 c2 c8 c4 c7
j5: c7 c3 c4 c1 c2 c6 c5 c8
j6: c6 c5 c4 c1 c3 c2 c7 c8
j7: c8 c2 c6 c4 c1 c3 c5 c7
j8: c2 c1 c5 c4 c6 c7 c8 c3


c1: j4 j5 j1 j7 j8 j6 j3 j2
c2: j6 j4 j2 j1 j5 j3 j8 j7
c3: j6 j2 j3 j4 j8 j7 j1 j5
c4: j2 j1 j3 j5 j6 j4 j8 j7
c5: j3 j5 j8 j7 j6 j1 j2 j4
c6: j8 j5 j6 j4 j2 j7 j3 j1
c7: j5 j8 j1 j3 j6 j4 j7 j2
c8: j5 j3 j7 j8 j1 j6 j4 j2

Expected:

c1 j4
c2 j8
c3 j2
c4 j1
c5 j3
c6 j6
c7 j5
c8 j7

Actual:

c1 j4
c2 j2
c3 j6
c4 j1
c5 j3
c6 j8
c7 j5
c8 j7

1. Actual: c2-j2 Expected: c2-j8

c2-j2 is stable becuase j2 is ranked higher than j8 from c2's perspective. Therefore, c2-j8 cannot be BOTH more relevant to each other.

2. Actual: c3-j6 Expected: c3-j2

c3-j6 is stable becuase j6 is ranked higher than j2 from c3's perspective. Therefore, c3-j2 cannot be BOTH more relevant to each other.

3. Actual: c6-j8 Expected: c6-j6

c6-j8 is stable becuase j8 is ranked higher than j6 from c6's perspective. Therefore, c6-j6 cannot be BOTH more relevant to each other.