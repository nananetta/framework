var session_idletime = {
	idleTime : 0,
	timeout_enabled:false,

	timerIncrement : function() {
		var idleTimeout=90;
		if (this.idleTime == null) {
			this.idleTime = 0;
		}
		this.idleTime = this.idleTime + 1;
		if (this.idleTime > idleTimeout) {
			window.location = contextPath + "/logout";
			alert("timeout");
		}
	},

	initIdleTimer : function(obj) {

		if (!this.timeout_enabled) {
			return;
		}
		// Zero the idle timer on mouse movement.
		$(obj).mousemove(function(e) {
			this.idleTime = 0;
		});
		$(obj).keypress(function(e) {
			this.idleTime = 0;
		});

		// Increment the idle time counter every minute.
		// var idleInterval = setInterval(timerIncrement, 60000); // 1 minute
		var idleInterval = setInterval(this.timerIncrement, 1000); // 1 second
	}

};
