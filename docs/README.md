# Engineer User Guide

Engineer is a Personal Assistant Chatbot that acts as a task manager to
helps users keep track of their tasks via a command line interface (CLI).

## Adding ToDos

This feature allows users to add and track tasks that are without any date/time 
attached to it, e.g., visit a museum, which are represented by the symbol "T".

Input format: `todo <task description>`

Example: `todo visit a museum`

Expected output:

```
Got it. I've added this task:
  [T][ ] visit a museum
Now you have 5 tasks in the list.
```

## Adding Deadlines

This feature allows users to add and track tasks that need to be done before a
specific date/time, e.g., finish proposal by 04/06/2025 9pm, which are represented 
by the symbol "D".

Input format: `deadline <task description> /by <deadline>`

Example: `deadline finish proposal /by 04/06/2025 9pm`

Expected output:

```
Got it. I've added this task:
  [D][ ] finish proposal (by: 04/06/2025 9pm)
Now you have 3 tasks in the list.
```

## Adding Events

This feature allows users to add and track tasks that start at a specific date/time
and ends at a specific date/time, e.g., basketball training 3-6pm, which are represented 
by the symbol "E".

Input format: `event <task description> /from <start time> /to <end time>`

Example: `event basketball training /from 3pm /to 6pm`

Expected output:

```
Got it. I've added this task:
  [E][ ] basketball training (from: 3pm to: 6pm)
Now you have 7 tasks in the list.
```

## Listing tasks

This feature allows users to view all the tasks that have been stored by Elyk.

Input format: `list`

Example: `list`

Expected output:

```
Here are the tasks in your list:
1.[T][X] visit a museum
2.[D][ ] finish proposal (by: 04/06/2025 9pm)
3.[E][ ] basketball training (from: 3pm to: 6pm)
4.[T][X] eat dinner
5.[T][ ] fitness exercises
```

## Marking tasks as Done

This feature allows users to mark tasks as done, which are represented 
by the symbol "X".

Input format: `mark <task index>`

Example: `mark 3`

Expected output:

```
Nice! I've marked this task as done:
  [E][X] basketball training (from: 3pm to: 6pm)
```

## Marking tasks as Not Done

This feature allows users to mark tasks as done, which are represented
by the symbol " ".

Input format: `unmark <task index>`

Example: `unmark 1`

Expected output:

```
OK, I've marked this task as not done yet:
  [T][ ] visit a museum
```

## Deleting tasks

This feature allows users to delete tasks that have been stored by Elyk.

Input format: `delete <task index>`

Example: `delete 2`

Expected output:

```
Noted. I've removed this task:
  [D][ ] finish proposal (by: 04/06/2025 9pm)
Now you have 4 tasks in the list.
```

## Finding tasks

This feature allows users to search for tasks that contain a certain keyword 
in the description.

Input format: `find <keyword>`

Example: `find run`

Expected output:

```
Here are the matching tasks in your list:
1.[T][X] run 10k
2.[T][ ] run 5k
3.[T][ ] charity run
4.[D][ ] slow run (by: 5pm)
5.[E][X] running exercise (from: 6pm to: 10pm)
```

## Exiting the program

This feature allows users to say bye to Engineer and exit the chatbot program.

Input format: `bye`

Example: `bye`

Expected output:

```
Bye. Hope to see you again soon!

Process finished with exit code 0
```
