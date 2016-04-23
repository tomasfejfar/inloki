using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace inloki.web.ViewModels
{
	public class BeaconViewModel
	{
		public string Id { get; set; }
		public string Description { get; set; }
		public bool Selected { get; set; }
	}
}