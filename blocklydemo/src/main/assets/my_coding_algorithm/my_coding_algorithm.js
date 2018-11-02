
Blockly.JavaScript['int_data'] = function(block) {
  var variable_name = Blockly.JavaScript.variableDB_.getName(block.getFieldValue('NAME'), Blockly.Variables.NAME_TYPE);
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};



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
  var code = "'digital_on':'" + dropdown_digital_select +"'";
  return code;
};


Blockly.JavaScript['equal'] = function(block) {
  var value_name1 = Blockly.JavaScript.valueToCode(block, 'NAME1', Blockly.JavaScript.ORDER_ATOMIC);
  var value_name2 = Blockly.JavaScript.valueToCode(block, 'NAME2', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};



Blockly.JavaScript['control_while_loop'] = function(block) {
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  // TODO: Assemble JavaScript into code variable.
  var code = "'for':[{" + value_name + "},{'control_type':1},{" + statements_name + "}]";
  return code;
};

Blockly.JavaScript['unequal2'] = function(block) {
  var value_name1 = Blockly.JavaScript.valueToCode(block, 'NAME1', Blockly.JavaScript.ORDER_ATOMIC);
  var value_name2 = Blockly.JavaScript.valueToCode(block, 'NAME2', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['int_data_change'] = function(block) {
  var variable_name1 = Blockly.JavaScript.variableDB_.getName(block.getFieldValue('NAME1'), Blockly.Variables.NAME_TYPE);
  var value_name = Blockly.JavaScript.valueToCode(block, 'NAME', Blockly.JavaScript.ORDER_ATOMIC);
  // TODO: Assemble JavaScript into code variable.
  var code = '...';
  // TODO: Change ORDER_NONE to the correct strength.
  return [code, Blockly.JavaScript.ORDER_NONE];
};

