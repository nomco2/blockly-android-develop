Blockly.JavaScript['control_while_loop'] = function(block) {
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = '...;\n';
  return code;
};

Blockly.JavaScript['digital_pin_on'] = function(block) {
  var dropdown_digital_select = block.getFieldValue('digital_select');
  // TODO: Assemble JavaScript into code variable.

  var code = "{'digital_on':'" + dropdown_digital_select + "'}";
  //code.concat("{'digital_on':'", dropdown_digital_select, "'}");
  return code;
};


