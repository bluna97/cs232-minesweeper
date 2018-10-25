| *Setting* | *Value*      |
| Library   | SwingLibrary |

| *Test Case*                      | *Action*                          | *Argument*              | *Argument*          |
| Test Game Starts                 | Start Application                 | edu.jsu.mcis.Main       |                     |
|                                  | Select Window                     | Minesweeper             |                     |
|                                  | Close Window                      | Minesweeper             |                     |
| Test Right Click Places Mine     | Start Application                 | edu.jsu.mcis.Main       |                     |
|                                  | Select Window                     | Minesweeper             |                     |
|                                  | Right Click On Component          | cell:3:5                |                     |
|                                  | ${tooltip}=                       | Get Tooltip Text        | cell:3:5            |
|                                  | Should Be Equal                   | flagged                 | ${tooltip}          |
|                                  | Close Window                      | Minesweeper             |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |
|                                  |                                   |                         |                     |




