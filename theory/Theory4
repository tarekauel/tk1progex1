4.1:
<definition
	targetNamespace="http://tk.informatik.tu-darmstadt.de"
	xmlns="http://schemas.xmlsoap.org/wsdl/"
   	xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
   	xmlns:xsd="http://www.w3.org/2001/XMLSchema">>

   	<types>
   		<schema>
   			<complexType name="Location">
   				<sequence>
					<element name="x" type="xsd:int"/>
   					<element name="y" type="xsd:int"/>
				</sequence>
   			</complexType>
   		</schema>
   	</types>

   	<message name="serviceId">
   		<part name="id" type="xsd:int" />
   	</message>

   	<message name="Location">
		<part name="id" type="xsd:Location" />
   	</message>

	<portType name="LocationService">
		<operation name="GetLocation">
			<input message="serviceId" />
			<output message="Location" />
		</operation>
	</portType>

	<binding name="LocationSOAPService" type="LocationService">
		<soap:binding style="rpc" transport="http://schemas.xmlsoap.org/soap/http"/>
		<operation name="GetLocation">
			<soap:operation soapAction="http://localhost/LocationSOAPService" style="rpc"/>
			<input>
	            <soap:body
	               encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"
	               use="encoded"/>
         	</input>
			<output>
	            <soap:body
	               encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"
	               use="encoded"/>
         	</output>
		</operation>

	</binding>

	<service name="GetLocationService">
		<port name="testPort" binding="LocationSOAPService">
            <soap:address location="http://localhost/LocationSOAPService"/>
        </port>
	</service>
</definition>

4.2:
			Rest:				SOAP:
Transport protocol:	Http				Http/Tcp
Typing:			
Data format:		JSON				XML
Addressing:		URL				XMLNamespace
State:			Stateless			Stateless
