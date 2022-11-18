LICENSE :
	This project is an open source project. It follows the GNU General Public License v3.0. For details, see [LICENSE](https://github.com/Douaol/ABChat/blob/main/LICENSE "LICENSE")<br>
Description :<br>
	This plugin can : <br>
	- Format the chat.<br>
	- Chat delay.<br>
	- Block messages with filter words.<br>
	- Block repeated messages.<br>
	- Block advertise messages.<br>
	- Block single words, like the 'L'.<br>
	- Block domain names outside the whitelist.<br>
	- Insert character emoticons in your chat.<br>
	- Change the join / quit message.<br>
	- Global mute.<br>
	Will be added in the future : <br>
	- Log.<br>
	- Anti Unicode.<br>
	- Configurable channels for each player.<br>
	- Configurable hover message ( regex ).<br>
	- More rich join/quit special effects.<br>
	- APIs for other plugins.<br>
Commands : <br>
	For ABChat :<br>
	- /abchat filter add (filter word) <br>
	- /abchat filter remove (filter word) <br>
	- /abchat globalmute<br>
	- /abchat reload<br>
	I don't think these instructions need explanation.<br>
Permissions : <br>
	For ABChat : <br>
	- abchat.use<br>
	"All command of ABChat"<br>
	- abchat.bypass.filter<br>
	"Bypass -> block messages with filter words"<br>
	- abchat.bypass.repeat<br>
	"Bypass -> block repeated messages"<br>
	- abchat.bypass.adv<br>
	"Bypass -> block advertise messages"<br>
	- abchat.bypass.domain<br>
	"Bypass -> block messages whit domain names"<br>
	- abchat.bypass.singlefilter<br>
	"Bypass -> block single words, like the 'L' "<br>
	- abchat.bypass.delay<br>
	"Bypass -> chat delay"<br>
emote.yml :<br>
	- permission/placeholder/replace<br>
	e.g. : <br>
	- emote.eg/eg/♥<br>
	If you have permission 'emote.eg' and send the 'eg' message, the final receipt will be '♥'<br>
	If you want to use '/' in emoticons, you should use '//' :<br>
	- emote.eg2/abc//ABC<br>
	send - > abc<br>
	receipt - > /ABC<br>
chat-format.yml :<br>
	Be careful! To use this function, you must have depend plugin : Vault<br>
	Placeholders without PlaceholderAPI :<br>
	- \<PLAYER\> <br>
	- \<PREFIX\> <br>
	- \<SUFFIX\> <br>
