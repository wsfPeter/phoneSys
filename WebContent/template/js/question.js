changeToType = function(type) {
	$("#addOption").hide();
	if (1 == type || 2 == type) {
		var options = [ 'A', 'B', 'C', 'D' ];
		$("#option").append('<table align="left" id="option_table">');
		$(options)
				.each(
						function(i) {
							var html = '<tr>';
							html += '<td>选项' + this + '</td>';
							if (1 == type) {
								html += '<td><input type="radio"  name="questionTest.answer" value="'
										+ this + '" /></td>';
							} else if (2 == type) {
								$("#addOption").show();
								html += '<td><input type="checkbox"  name="questionTest.answer" value="'
										+ this + '" /></td>';
							}
							html += '<td><textarea rows="2" cols="40" id="option'
									+ this
									+ '" name="options"></textarea></td>';
							html += '</tr>';
							$("#option_table").append(html);
						});
		$("#option").append('</table>');
	} else {
		$("#option").append('<table align="left" id="option_table">');
		var html = '<tr>';
		html += '<td>选项A</td>';
		html += '<td><input type="radio"  name="questionTest.answer" value="A" /></td>';
		html += '<td><textarea rows="2" cols="40" id="optionA" name="options"></textarea></td>';
		html += '</tr>';
		
		html += '<tr>';
		html += '<td>选项B</td>';
		html += '<td><input type="radio"  name="questionTest.answer" value="B" /></td>';
		html += '<td><textarea rows="2" cols="40" id="optionB" name="options"></textarea></td>';
		html += '</tr>';
		
		$("#option_table").append(html);
	}
}

// 选择题增加选项
addOption = function() {
	var options = [ 'A', 'B', 'C', 'D', 'E', 'F' ];
	for (var i = 0; i < options.length; i = i + 1) {
		if (!document.getElementById("option" + options[i])) {
			var html = '<tr>';
			html += "<td>选项 <a>" + options[i] + "</td>";
			html += '<td><input type="checkbox"  name="questionTest.answer" value="'
					+ options[i] + '" /></td>';
			var aremove = '<a href="javascript:;" onclick="$(this).parent().parent().remove()">移除</a>';
			html += '<td><textarea rows="2" cols="40" name="options" id="option'
					+ options[i]
					+ '" name="options"></textarea> '
					+ aremove
					+ '</td>';
			html += '</tr>';
			$("#option_table").append(html);
			break;
		}
	}
}

