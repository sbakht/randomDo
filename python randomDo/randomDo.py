import sys
import re
import random
##can't have priority for imported files

taskList = []

def add_task(priorityChoice):
  if priorityChoice == 'on':
    title = raw_input('Please enter your task (Enter \"done\" when finished): ')
    
    if title.lower() != 'done':
      priority = raw_input('Please enter the priority(high, normal, low): ')
      tuple = (title, priority)
      taskList.append(tuple)
      add_task(priorityChoice)
    else:
      return 
  else:
    title = raw_input('Please enter your task (Enter \"done\" when finished): ')

    
    if title.lower() != 'done':
      tuple = (title, 'normal') #sets all priority to normal when priority setting is off
      taskList.append(tuple) 
      add_task(priorityChoice)
    else:
      return       
      

def delete_task():
  taskToDelete = raw_input('Enter name of task to delete: ')
  for x in taskList:
    print x[0]
    if x[0].lower() == taskToDelete.lower():
      taskList.remove(x)
  return

def sortKey(x):
  return x[1]


def main():
  args = sys.argv[1:]
    
  while 1:
    cmd = raw_input('Enter your command: ')
    if cmd == 'add':
      while 1:
        priorityChoice = raw_input('Priority on or off?: ')
        if priorityChoice == 'on' or priorityChoice == 'off' :
          break
      add_task(priorityChoice)
    elif cmd == 'delete':
      delete_task()
    elif cmd == 'view':
      i = 1
      random.shuffle(taskList)
      for x in taskList:
        print str(i) + ': ' + x[0] + '    ' + x[1]
        i += 1
    elif cmd == 'start':
      i = 0
      x = 1
      reshuffleOnce = -1
      random.shuffle(taskList)
      while i < len(taskList):
        print '##### ' + str(x) + ': ' + taskList[0][0] + ' #####\n'
        reshuffle = raw_input('Press Enter when you have completed this task or \"reshuffle\" once each task #\n')
        if reshuffle == 'reshuffle':
          if reshuffleOnce == x:
            continue
          else:
            reshuffleOnce = x
            random.shuffle(taskList)
            i = 0
        else:
          taskList.remove(taskList[0])
          x += 1
      print 'CONGRADULATIONS, you have completed all your tasks'
    elif cmd == 'export':
      i = 1
      for x in sorted(taskList,key=sortKey):
        output += str(i) + ': ' + x[0] + '    ' + x[1] + '\n'
        i += 1   
      f = open('tasks', 'w')
      f.write(output)
    elif cmd == 'import': #reads tasks.txt of tasks instead of imputting manually using add command
      f = open('tasks.txt')
      for title in f:
        tuple = (title.rstrip('\n'), 'normal') #sets all priority to normal when priority setting is off
        taskList.append(tuple) 
    elif cmd == 'exit':
      break

if __name__ == '__main__':
  main()