LICENSE :
	This project is an open source project. It follows the GNU General Public License v3.0. For details, see [LICENSE](https://github.com/Douaol/ABChat/blob/main/LICENSE "LICENSE")
Description :
	This plugin can : 
	- Format the chat.
	- Chat delay.
	- Block messages with filter words.
	- Block repeated messages.
	- Block advertise messages.
	- Block single words, like the 'L'.
	- Block domain names outside the whitelist.
	- Insert character emoticons in your chat.
	- Change the join / quit message.
	- Global mute.
	Will be added in the future : 
	- Log.
	- Anti Unicode.
	- Configurable channels for each player.
	- Configurable hover message ( regex ).
	- More rich join/quit special effects.
	- APIs for other plugins.
Commands : 
	For ABChat :
	- /abchat filter add <filter word>
	- /abchat filter remove <filter word>
	- /abchat globalmute
	- /abchat reload
	I don't think these instructions need explanation.
Permissions : 
	For ABChat : 
	- abchat.use
	"All command of ABChat"
	- abchat.bypass.filter
	"Bypass -> block messages with filter words"
	- abchat.bypass.repeat
	"Bypass -> block repeated messages"
	- abchat.bypass.adv
	"Bypass -> block advertise messages"
	- abchat.bypass.domain
	"Bypass -> block messages whit domain names"
	- abchat.bypass.singlefilter
	"Bypass -> block single words, like the 'L' "
	- abchat.bypass.delay
	"Bypass -> chat delay"
Pictures : 
![[Pasted image 20221118080519.png]]
![[Pasted image 20221118080615.png]]
![[Pasted image 20221118080705.png]]
![[Pasted image 20221118080644.png]]
![[Pasted image 20221118080747.png]]
![[Pasted image 20221118080818.png]]
![[Pasted image 20221118080849.png]]
![[Pasted image 20221118080955.png]]
![[Pasted image 20221118081029.png]]
![[Pasted image 20221118081129.png]]
![[Pasted image 20221118081206.png]]

emote.yml :
	![[Pasted image 20221118081405.png]]
	- permission/placeholder/replace
	e.g. : 
	- emote.eg/eg/♥
	If you have permission 'emote.eg' and send the 'eg' message, the final receipt will be '♥'
	If you want to use '/' in emoticons, you should use '//' :
	- emote.eg2/abc//ABC
	send - > abc
	receipt - > /ABC
chat-format.yml :
	![[Pasted image 20221118082110.png]]
	Be careful! To use this function, you must have depend plugin : Vault
	Placeholders without PlaceholderAPI :
	- <PLAYER>
	- <PREFIX>
	- <SUFFIX>
