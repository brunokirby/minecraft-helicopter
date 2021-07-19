TODO list
=========

Planning for release:
- update to Minecraft 1.16.5
- test as a jarfile
- write README
- testing
- licence

- what should it drop if broken? parts (rotor etc)? 
- height of base?
- icon for helicopter
- cleanup
- transparent floor
- sounds
- Minecraft 1.17 etc
- helicopter landing on head should be fatal
- make a release / zipfile
- write a README / documentation for github
- fix committer history
optional:
  - aluminium

motion planning:
- "2" WASD does pitch/roll
- "3" move follows pitch/roll  
  while stationary, A/D does a "strafe"
  while moving, A/D does a banked curve
- "4" L/R keys do stationary yaw  
  (& while moving, slows you down & maybe off-bank & maybe crash)

---------------------------------------------



Done:
====

- shouldn't be pushable
- translation (in inventory)
- currently drops item.air but that can spawn helicopter (is weird)
- it's facing backwards :-)
- fix player height in vehicle when mounted / edit model
- craft from parts
- use the fine texture
- custom key commands
- size of hitbox
- position after dismount
- placement rules (OK as they are, given hitbox etc)

motion planning:
- "1" up/down does takeoff/landing
  - start engine key?
    concepts of "flying" / "not flying"  
