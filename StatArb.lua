package.cpath = getScriptPath() .. "\\5.3\\?.dll;" .. package.cpath
package.path = getScriptPath() .. "\\\\5.3\\?.lua;" .. package.path
is_run = false

function getConnection()
	socket = require("socket.core")
	host = "localhost"
	port = 8000
	c = assert(socket.connect("localhost", 8000),"Error, need to start a Java first!")
	message("Connection succeeded!")
end

function formMesssage(class_code, sec_code)
	if (class_code == class_code_one and sec_code == sec_code_one)then
		tb=getQuoteLevel2(class_code, sec_code)
		offerPrice1st = sec_code.." "..tostring(tb.offer[1].price)
	end
	if (class_code == class_code_sec and sec_code == sec_code_sec)then
		tb=getQuoteLevel2(class_code, sec_code)
		offerPrice2nd = sec_code.." "..tostring(tb.offer[1].price)
	end
	
end
function sendMessage(out,message)
	if(message~="") then
	out:send(message.."\n")
	end
	--elseif () then
	--
	--end
end

function OnInit()
    is_run = true
	offerPrice1st = ""
	offerPrice2nd = ""
	class_code_one = "TQBR"
	sec_code_one = "MTLR"
	class_code_sec = "TQBR"
	sec_code_sec = "MTLRP"
	getConnection()
	
	
end


function main()
    while is_run do
		sleep(2000)
		sendMessage(c,offerPrice1st)
		sendMessage(c,offerPrice2nd)
		
    end
end

function OnStop(stop_flag)
    is_run = false
end

function OnQuote(class_code, sec_code)
	formMesssage(class_code, sec_code)
end